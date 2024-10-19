package com.csse.mycare.masterservice.service;

import com.csse.mycare.common.CalendarUtil;
import com.csse.mycare.masterservice.dao.Appointment;
import com.csse.mycare.masterservice.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointment(Integer id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(Integer id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getAppointmentsByPatient(Integer patientId) {
        return getAllAppointments().stream().filter(appointment -> appointment.getPatient().getUserId()
                .equals(patientId)).toList();
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(Integer doctorId) {
        return getAllAppointments().stream().filter(appointment -> appointment.getDoctor().getUserId()
                .equals(doctorId)).toList();
    }
}
