package com.csse.mycare.masterservice;

import com.csse.mycare.common.exceptions.InvalidAppointmentTimeException;
import com.csse.mycare.masterservice.dao.Appointment;
import com.csse.mycare.masterservice.dao.Doctor;
import com.csse.mycare.patient.dto.AppointmentRequest;
import com.csse.mycare.patient.dto.AppointmentResponse;
import com.csse.mycare.patient.dto.DoctorAvailabilityRequest;
import com.csse.mycare.patient.dto.DoctorAvailabilityResponse;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface MasterService {
    // Appointment
    Appointment saveAppointment(Appointment appointment);

    Appointment getAppointment(Integer id);

    Appointment updateAppointment(Appointment appointment);

    void deleteAppointment(Integer id);

    List<Appointment> getAllAppointments();

    public List<Appointment> getAppointmentsBySchedule(Integer scheduleId);

    public List<Appointment> getAppointmentsByScheduleAndDay(Integer scheduleId, Date date);

    // Doctor
    List<Doctor> getAllDoctors();

    Doctor getDoctorById(int id);

    Doctor saveDoctor(Doctor doctor);

    Doctor updateDoctor(Doctor doctor);

    void deleteDoctor(int id);

    // Patient
    public List<Appointment> getAppointmentsByPatient(Integer patientId);

    // Standalone Methods
    public DoctorAvailabilityResponse getDoctorAvailableDates(DoctorAvailabilityRequest request);

    public AppointmentResponse createAppointmentWithDoctor(AppointmentRequest appointmentRequest) throws InvalidAppointmentTimeException, ParseException;
}
