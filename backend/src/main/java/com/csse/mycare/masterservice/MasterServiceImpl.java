package com.csse.mycare.masterservice;

import com.csse.mycare.admin.dto.DoctorRegistrationRequest;
import com.csse.mycare.admin.dto.PharmacyRegistrationRequest;
import com.csse.mycare.common.CalendarUtil;
import com.csse.mycare.common.constants.Role;
import com.csse.mycare.common.exceptions.InvalidAppointmentTimeException;
import com.csse.mycare.common.exceptions.UserRegistrationException;
import com.csse.mycare.masterservice.dao.Appointment;
import com.csse.mycare.masterservice.dao.Doctor;
import com.csse.mycare.masterservice.service.AppointmentService;
import com.csse.mycare.masterservice.service.DoctorService;
import com.csse.mycare.masterservice.service.PatientService;
import com.csse.mycare.masterservice.service.PharmacyService;
import com.csse.mycare.patient.dto.AppointmentRequest;
import com.csse.mycare.patient.dto.AppointmentResponse;
import com.csse.mycare.patient.dto.DoctorAvailabilityRequest;
import com.csse.mycare.patient.dto.DoctorAvailabilityResponse;
import com.csse.mycare.security.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Slf4j
@Service
public class MasterServiceImpl implements MasterService {
    AppointmentService appointmentService;
    DoctorService doctorService;
    PatientService patientService;
    PharmacyService pharmacyService;
    AuthenticationService authenticationService;

    @Autowired
    public MasterServiceImpl(
            AppointmentService appointmentService,
            DoctorService doctorService,
            PatientService patientService,
            PharmacyService pharmacyService,
            AuthenticationService authenticationService
    ) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.pharmacyService = pharmacyService;
        this.authenticationService = authenticationService;
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentService.saveAppointment(appointment);
    }

    @Override
    public Appointment getAppointment(Integer id) {
        return appointmentService.getAppointment(id);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        return appointmentService.updateAppointment(appointment);
    }

    @Override
    public void deleteAppointment(Integer id) {
        appointmentService.deleteAppointment(id);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @Override
    public List<Appointment> getAppointmentsBySchedule(Integer scheduleId) {
        return appointmentService.getAppointmentsBySchedule(scheduleId);
    }

    @Override
    public List<Appointment> getAppointmentsByScheduleAndDay(Integer scheduleId, Date date) {
        return appointmentService.getAppointmentsByScheduleAndDay(scheduleId, date);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @Override
    public Doctor getDoctorById(int id) {
        return doctorService.getDoctorById(id);
    }

    @Override
    public Boolean saveDoctor(DoctorRegistrationRequest doctor) throws UserRegistrationException {
        return authenticationService.registerWithRole(doctor, Role.DOCTOR);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        return doctorService.updateDoctor(doctor);
    }

    @Override
    public void deleteDoctor(int id) {
        doctorService.deleteDoctor(id);
    }

    @Override
    public List<Appointment> getAppointmentsByPatient(Integer patientId) {
        return appointmentService.getAppointmentsByPatient(patientId);
    }

    @Override
    public Boolean savePharmacy(PharmacyRegistrationRequest pharmacy) throws UserRegistrationException {
        return authenticationService.registerWithRole(pharmacy, Role.PHARMACY);
    }

    /**
     * Get available slots for a doctor on a given date
     * The availability is as follows. The start of the available time is the key
     * and the value is the duration in minutes for where the doctor is available
     *
     * @param request DoctorAvailabilityRequest
     * @return DoctorAvailabilityResponse
     */
    @Override
    public DoctorAvailabilityResponse getDoctorAvailableDates(DoctorAvailabilityRequest request) {
        List<Appointment> appointments = getAppointmentsByScheduleAndDay(
                doctorService.getDoctorById(request.getDoctorId()).getSchedule().getId(), request.getDate());
        DoctorAvailabilityResponse availabilityResponse = new DoctorAvailabilityResponse();
        Map<Date, Integer> availableSlots = new HashMap<>();

        appointments.sort(Comparator.comparing(Appointment::getAppointmentStart));
        availabilityResponse.setDoctor(doctorService.getDoctorById(request.getDoctorId()));

        Date startOfDay = CalendarUtil.getStartOfDay(request.getDate());
        Date endOfDay = CalendarUtil.getEndOfDay(request.getDate());

        Date previousEndTime = startOfDay;

        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentStart().after(previousEndTime) && appointment.getAppointmentStart().before(endOfDay)) {
                availableSlots.put(previousEndTime, CalendarUtil.getDifferenceInMinutes(previousEndTime,
                        appointment.getAppointmentStart()));
            }

            previousEndTime = CalendarUtil.addMinutes(appointment.getAppointmentStart(), appointment.getDuration());
        }

        if (appointments.isEmpty()) {
            availableSlots.put(startOfDay, CalendarUtil.getDifferenceInMinutes(startOfDay, endOfDay));
        }

        availabilityResponse.setFreeSlots(availableSlots);

        return availabilityResponse;
    }

    /**
     * Create an appointment with a doctor
     *
     * @param appointmentRequest AppointmentRequest
     * @return AppointmentResponse
     * @throws InvalidAppointmentTimeException
     * @throws ParseException
     */
    @Override
    public AppointmentResponse createAppointmentWithDoctor(AppointmentRequest appointmentRequest) throws InvalidAppointmentTimeException, ParseException {
        DoctorAvailabilityRequest availabilityRequest = new DoctorAvailabilityRequest();
        availabilityRequest.setDoctorId(appointmentRequest.getDoctorId());
        availabilityRequest.setDate(CalendarUtil.parseDate(appointmentRequest.getAppointmentStart(),
                CalendarUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS));
        DoctorAvailabilityResponse availabilityResponse = getDoctorAvailableDates(availabilityRequest);

        if (availabilityResponse.getFreeSlots().containsKey(CalendarUtil.parseDate(appointmentRequest.getAppointmentStart(),
                CalendarUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS))) {
            Appointment appointment = new Appointment();
            Doctor doctor = doctorService.getDoctorById(appointmentRequest.getDoctorId());
            appointment.setAppointmentStart(CalendarUtil.parseDate(appointmentRequest.getAppointmentStart(),
                    CalendarUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS));
            appointment.setDuration(appointmentRequest.getAppointmentLength());
            appointment.setSchedule(doctor.getSchedule());
            appointment.setPayment(null); // TODO: Implement payment
            appointment.setPatient(patientService.getPatient(appointmentRequest.getPatientId()));
            saveAppointment(appointment);
            AppointmentResponse response = new AppointmentResponse(
                    CalendarUtil.formatDate(appointment.getAppointmentStart(), CalendarUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS),
                    appointment.getDuration().toString(),
                    appointmentRequest.getPatientId(),
                    doctor.getUserId(),
                    true
            );
            log.info("Appointment created: {}", response);
            return response;
        } else {
            log.warn("Appointment creation attempted for unavailable time: {}", appointmentRequest.getAppointmentStart());
            throw new InvalidAppointmentTimeException();
        }
    }
}
