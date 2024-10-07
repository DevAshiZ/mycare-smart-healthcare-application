package com.csse.mycare.masterservice;

import com.csse.mycare.masterservice.dao.Appointment;
import com.csse.mycare.masterservice.dao.Doctor;

import java.util.List;

public interface MasterService {
    // Appointment
    Appointment saveAppointment(Appointment appointment);

    Appointment getAppointment(Integer id);

    Appointment updateAppointment(Appointment appointment);

    void deleteAppointment(Integer id);

    List<Appointment> getAllAppointments();

    public List<Appointment> getAppointmentsBySchedule(Integer scheduleId);

    // Doctor
    List<Doctor> getAllDoctors();

    Doctor getDoctorById(int id);

    Doctor saveDoctor(Doctor doctor);

    Doctor updateDoctor(Doctor doctor);

    void deleteDoctor(int id);
}
