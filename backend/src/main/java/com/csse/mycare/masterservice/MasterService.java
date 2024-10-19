package com.csse.mycare.masterservice;

import com.csse.mycare.admin.dto.DoctorRegistrationRequest;
import com.csse.mycare.admin.dto.PharmacyRegistrationRequest;
import com.csse.mycare.admin.dto.ScheduleRequest;
import com.csse.mycare.common.exceptions.*;
import com.csse.mycare.doctor.dto.PrescriptionDTO;
import com.csse.mycare.masterservice.dao.*;
import com.csse.mycare.patient.dto.AppointmentRequest;
import com.csse.mycare.patient.dto.AppointmentResponse;

import java.text.ParseException;
import java.util.List;

public interface MasterService {
    // Appointment
    Appointment saveAppointment(Appointment appointment);

    Appointment getAppointment(Integer id);

    Appointment updateAppointment(Appointment appointment);

    void deleteAppointment(Integer id);

    List<Appointment> getAllAppointments();

    // Doctor
    List<Doctor> getAllDoctors();

    Doctor getDoctorById(int id);

    public Boolean saveDoctor(DoctorRegistrationRequest doctor) throws UserRegistrationException, UserAlreadyExistsException;

    Doctor updateDoctor(DoctorRegistrationRequest doctor) throws ReferedDoctorNotFoundException;

    void deleteDoctor(int id);

    // Patient
    public List<Appointment> getAppointmentsByPatient(Integer patientId);

    // Pharmacy
    public Boolean savePharmacy(PharmacyRegistrationRequest pharmacy) throws UserRegistrationException, UserAlreadyExistsException;

    public Pharmacy updatePharmacy(PharmacyRegistrationRequest pharmacy) throws ReferedPharmacyNotFoundException;

    public void deletePharmacy(Integer pharmacyId);

    public List<Pharmacy> getAllPharmacies();


    // Schedule
    public List<Schedule> getAllSchedules();

    public Schedule saveSchedule(ScheduleRequest schedule);

    public List<Schedule> getSchedulesByDay(String day);

    public List<Schedule> getSchedulesByDoctorId(Integer doctorId);

    // Medicine
    public List<Medicine> getAllMedicines();

    public Medicine saveMedicine(Medicine medicine);

    public Medicine getMedicineById(Integer id);

    public Medicine updateMedicine(Medicine medicine);

    public void deleteMedicine(Integer id);

    // Prescription
    Prescription findPrescriptionById(Long id);

    List<Prescription> findAllPrescriptions();

    Prescription savePrescription(PrescriptionDTO prescription) throws ParseException;

    void deletePrescription(Integer id);

    // Standalone Methods
    //public DoctorAvailabilityResponse getDoctorAvailableDates(DoctorAvailabilityRequest request);

    public AppointmentResponse createAppointmentWithDoctor(AppointmentRequest appointmentRequest) throws InvalidAppointmentTimeException, ParseException, AppointmentAlreadyExistsException;
}
