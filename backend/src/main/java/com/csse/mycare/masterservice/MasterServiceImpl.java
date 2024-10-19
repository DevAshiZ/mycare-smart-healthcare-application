package com.csse.mycare.masterservice;

import com.csse.mycare.admin.dto.DoctorRegistrationRequest;
import com.csse.mycare.admin.dto.PharmacyRegistrationRequest;
import com.csse.mycare.admin.dto.ScheduleRequest;
import com.csse.mycare.common.CalendarUtil;
import com.csse.mycare.common.constants.PaymentMethod;
import com.csse.mycare.common.constants.Role;
import com.csse.mycare.common.exceptions.AppointmentAlreadyExistsException;
import com.csse.mycare.common.exceptions.ReferedDoctorNotFoundException;
import com.csse.mycare.common.exceptions.ReferedPharmacyNotFoundException;
import com.csse.mycare.common.exceptions.UserAlreadyExistsException;
import com.csse.mycare.doctor.dto.PrescriptionDTO;
import com.csse.mycare.common.exceptions.*;
import com.csse.mycare.masterservice.dao.*;
import com.csse.mycare.masterservice.service.*;
import com.csse.mycare.patient.dto.*;
import com.csse.mycare.security.service.AuthenticationService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
    MedicineService medicineService;
    PaymentService paymentService;
    PrescriptionService prescriptionService;

    @Autowired
    public MasterServiceImpl(
            AppointmentService appointmentService,
            DoctorService doctorService,
            PatientService patientService,
            PharmacyService pharmacyService,
            AuthenticationService authenticationService,
            ScheduleService scheduleService,
            MedicineService medicineService,
            PaymentService paymentService,
            PrescriptionService prescriptionService
    ) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.pharmacyService = pharmacyService;
        this.authenticationService = authenticationService;
        this.scheduleService = scheduleService;
        this.medicineService = medicineService;
        this.paymentService = paymentService;
        this.prescriptionService = prescriptionService;
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

    @Override
    public List<Medicine> getAllMedicines() {
        return medicineService.getAllMedicines();
    }

    @Override
    public Medicine saveMedicine(Medicine medicine) {
        return medicineService.saveMedicine(medicine);
    }

    @Override
    public Medicine getMedicineById(Integer id) {
        return medicineService.getMedicine(id);
    }

    @Override
    public Medicine updateMedicine(Medicine medicine) {
        return medicineService.updateMedicine(medicine);
    }

    @Override
    public void deleteMedicine(Integer id) {
        medicineService.deleteMedicine(id);
    }

    @Override
    public Prescription findPrescriptionById(Long id) {
        return prescriptionService.findPrescriptionById(id);
    }

    @Override
    public List<Prescription> findAllPrescriptions() {
        return prescriptionService.findAllPrescriptions();
    }

    @Override
    @Transactional
    public Prescription savePrescription(PrescriptionDTO prescription) throws ParseException {
        Prescription newPrescription = new Prescription();
        List<Medicine> savedMedicines = new ArrayList<>();
        for (Medicine medicine : prescription.getMedicines()){
            medicine = medicineService.saveMedicine(medicine);
            savedMedicines.add(medicine);
        }
        newPrescription.setMedicines(savedMedicines);
        newPrescription.setDoctor(doctorService.getDoctorById(prescription.getDoctorId()));
        newPrescription.setPatient(patientService.getPatient(prescription.getPatientId()));
        newPrescription.setIssueDate(CalendarUtil.parseISO8601Date(prescription.getIssueDate()));

        return prescriptionService.savePrescription(newPrescription);
    }

    @Override
    public void deletePrescription(Integer id) {
        prescriptionService.deletePrescription(id);
    }

    @Override
    public PaymentResponse makeCardPayment(CardPaymentRequest cardPaymentRequest) throws PaymentAlreadyMadeException, PaymentFailedException {
        CardPayment cardPayment = new CardPayment();

        cardPayment.setUserId(cardPaymentRequest.getUserId());
        cardPayment.setAppointmentId(cardPaymentRequest.getAppointmentId());
        cardPayment.setAmount(cardPaymentRequest.getAmount());
        cardPayment.setPaymentMethod(PaymentMethod.CARD);
        cardPayment.setPaymentDateTime(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));
        cardPayment.setIsPaid(true);
        cardPayment.setCardNumber(cardPaymentRequest.getCardNumber());
        cardPayment.setCardHolderName(cardPaymentRequest.getCardHolderName());
        cardPayment.setExpiryDate(cardPaymentRequest.getExpiryDate());
        cardPayment.setCvv(cardPaymentRequest.getCvv());

        try {
            cardPayment = paymentService.createCardPayment(cardPayment);
            Appointment appointment = appointmentService.getAppointment(cardPayment.getAppointmentId());
            appointment.setPayment(cardPayment);
            appointmentService.saveAppointment(appointment);
        }
        catch (PaymentAlreadyMadeException e) {
            throw new PaymentAlreadyMadeException();
        }
        catch (Exception e) {
            throw new PaymentFailedException();
        }

        return PaymentResponse.builder()
                .transactionId(cardPayment.getId().toString())
                .appointmentID(cardPayment.getAppointmentId().toString())
                .paymentAmount(cardPayment.getAmount())
                .paymentMethod(cardPayment.getPaymentMethod().toString())
                .paymentDateTime(cardPayment.getPaymentDateTime())
                .isPaid(cardPayment.getIsPaid())
                .build();

    }

    @Override
    public PaymentResponse makeCashPayment(CashPaymentRequest cashPaymentRequest) throws PaymentAlreadyMadeException, PaymentFailedException {

        CashPayment cashPayment = new CashPayment();

        cashPayment.setUserId(cashPaymentRequest.getUserId());
        cashPayment.setAppointmentId(cashPaymentRequest.getAppointmentId());
        cashPayment.setAmount(cashPaymentRequest.getAmount());
        cashPayment.setPaymentMethod(PaymentMethod.CARD);
        cashPayment.setPaymentDateTime(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));
        cashPayment.setIsPaid(true);
        cashPayment.setUserEmail(cashPaymentRequest.getUserEmail());

        try {
            cashPayment = paymentService.createCashPayment(cashPayment);
            Appointment appointment = appointmentService.getAppointment(cashPayment.getAppointmentId());
            appointment.setPayment(cashPayment);
            appointmentService.saveAppointment(appointment);
        }
        catch (PaymentAlreadyMadeException e) {
            throw new PaymentAlreadyMadeException();
        }
        catch (Exception e) {
            throw new PaymentFailedException();
        }

        return PaymentResponse.builder()
                .transactionId(cashPayment.getId().toString())
                .appointmentID(cashPayment.getAppointmentId().toString())
                .paymentAmount(cashPayment.getAmount())
                .paymentMethod(cashPayment.getPaymentMethod().toString())
                .paymentDateTime(cashPayment.getPaymentDateTime())
                .isPaid(cashPayment.getIsPaid())
                .build();
    }

    @Override
    public List<PaymentResponse> getAllPaymentsByUserId(Integer userId) {
        return paymentService.getAllPaymentsByUserId(userId);
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
