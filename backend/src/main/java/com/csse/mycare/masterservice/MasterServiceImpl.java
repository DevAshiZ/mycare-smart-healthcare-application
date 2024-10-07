package com.csse.mycare.masterservice;

import com.csse.mycare.masterservice.dao.Appointment;
import com.csse.mycare.masterservice.dao.Doctor;
import com.csse.mycare.masterservice.service.AppointmentService;
import com.csse.mycare.masterservice.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterServiceImpl implements MasterService {
    AppointmentService appointmentService;
    DoctorService doctorService;

    @Autowired
    public MasterServiceImpl(
            AppointmentService appointmentService,
            DoctorService doctorService
    ) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
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
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @Override
    public Doctor getDoctorById(int id) {
        return doctorService.getDoctorById(id);
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorService.saveDoctor(doctor);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        return doctorService.updateDoctor(doctor);
    }

    @Override
    public void deleteDoctor(int id) {
        doctorService.deleteDoctor(id);
    }
}
