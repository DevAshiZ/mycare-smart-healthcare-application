package com.csse.mycare.masterservice.service;

import com.csse.mycare.masterservice.dao.Appointment;

import java.util.Date;
import java.util.List;

public interface AppointmentService {
    Appointment saveAppointment(Appointment appointment);
    Appointment getAppointment(Integer id);
    Appointment updateAppointment(Appointment appointment);
    void deleteAppointment(Integer id);
    List<Appointment> getAllAppointments();
    List<Appointment> getAppointmentsByPatient(Integer patientId);
    List<Appointment> getAppointmentsBySchedule(Integer scheduleId);
    public List<Appointment> getAppointmentsByScheduleAndDay(Integer scheduleId, Date date);
}
