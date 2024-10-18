package com.csse.mycare.masterservice;

import com.csse.mycare.admin.dto.DoctorRegistrationRequest;
import com.csse.mycare.admin.dto.PharmacyRegistrationRequest;
import com.csse.mycare.admin.dto.ScheduleRequest;
import com.csse.mycare.common.CalendarUtil;
import com.csse.mycare.common.constants.Role;
import com.csse.mycare.common.exceptions.AppointmentAlreadyExistsException;
import com.csse.mycare.common.exceptions.ReferedDoctorNotFoundException;
import com.csse.mycare.common.exceptions.ReferedPharmacyNotFoundException;
import com.csse.mycare.common.exceptions.UserAlreadyExistsException;
import com.csse.mycare.masterservice.dao.Appointment;
import com.csse.mycare.masterservice.dao.Doctor;
import com.csse.mycare.masterservice.dao.Pharmacy;
import com.csse.mycare.masterservice.dao.Schedule;
import com.csse.mycare.masterservice.service.*;
import com.csse.mycare.patient.dto.AppointmentRequest;
import com.csse.mycare.patient.dto.AppointmentResponse;
import com.csse.mycare.security.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Slf4j
@Service
public class MasterServiceImpl implements MasterService {
    AppointmentService appointmentService;
    DoctorService doctorService;
    PatientService patientService;
    PharmacyService pharmacyService;
    AuthenticationService authenticationService;
    ScheduleService scheduleService;

    @Autowired
    public MasterServiceImpl(
            AppointmentService appointmentService,
            DoctorService doctorService,
            PatientService patientService,
            PharmacyService pharmacyService,
            AuthenticationService authenticationService,
            ScheduleService scheduleService
    ) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.pharmacyService = pharmacyService;
        this.authenticationService = authenticationService;
        this.scheduleService = scheduleService;
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
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @Override
    public Doctor getDoctorById(int id) {
        return doctorService.getDoctorById(id);
    }

    @Override
    public Boolean saveDoctor(DoctorRegistrationRequest doctor) throws UserAlreadyExistsException {
        return authenticationService.registerWithRole(doctor, Role.DOCTOR);
    }

    @Override
    public Doctor updateDoctor(DoctorRegistrationRequest doctor) throws ReferedDoctorNotFoundException {
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
    public Boolean savePharmacy(PharmacyRegistrationRequest pharmacy) throws UserAlreadyExistsException {
        return authenticationService.registerWithRole(pharmacy, Role.PHARMACY);
    }

    @Override
    public Pharmacy updatePharmacy(PharmacyRegistrationRequest pharmacy) throws ReferedPharmacyNotFoundException {
        return pharmacyService.updatePharmacy(pharmacy);
    }

    @Override
    public void deletePharmacy(Integer pharmacyId) {
        pharmacyService.deletePharmacy(pharmacyId);
    }

    /**
     * Get all pharmacies
     *
     * @return List of pharmacies
     */
    @Override
    public List<Pharmacy> getAllPharmacies() {
        List<Pharmacy> pharmacies = pharmacyService.getAllPharmacies();
        pharmacies.forEach(pharmacy -> pharmacy.setPassword(null)); // Remove password from response
        return pharmacies;
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return List.of();
    }

    @Override
    public Schedule saveSchedule(ScheduleRequest schedule) {
        Doctor doctor = doctorService.getDoctorById(Integer.parseInt(schedule.getDoctorId()));
        Schedule savedSchedule = scheduleService.saveSchedule(schedule);
        doctor.setSchedule(savedSchedule);
        doctorService.saveDoctor(doctor);
        return savedSchedule;
    }

    @Override
    public List<Schedule> getSchedulesByDay(String day) {
        return scheduleService.getSchedulesByDay(day);
    }

    @Override
    public List<Schedule> getSchedulesByDoctorId(Integer doctorId) {
        return scheduleService.getSchedulesByDoctorId(doctorId);
    }

    /**
     * Create an appointment with a doctor
     *
     * @param appointmentRequest AppointmentRequest
     * @return AppointmentResponse
     */
    @Override
    public AppointmentResponse createAppointmentWithDoctor(AppointmentRequest appointmentRequest) throws ParseException, AppointmentAlreadyExistsException {
        Appointment appointment = new Appointment();
        appointment.setAppointmentStart(CalendarUtil.parseISO8601Date(appointmentRequest.getAppointmentStart()));
        appointment.setDuration(appointmentRequest.getAppointmentLength());
        appointment.setDoctor(doctorService.getDoctorById(appointmentRequest.getDoctorId()));
        appointment.setPatient(patientService.getPatient(appointmentRequest.getPatientId()));
        try {
            appointment = appointmentService.saveAppointment(appointment);
        } catch (Exception e) {
            throw new AppointmentAlreadyExistsException();
        }

        return new AppointmentResponse(
                appointment.getAppointmentStart().toString(),
                appointment.getDuration(),
                appointment.getDoctor().getUserId(),
                appointment.getPatient().getUserId(),
                true
        );
    }
}
