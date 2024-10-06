package com.csse.mycare.masterservice.repository;

import com.csse.mycare.masterservice.dao.Appointment;
import com.csse.mycare.masterservice.dao.Doctor;
import com.csse.mycare.masterservice.dao.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    Optional<Appointment> findAppointmentByDoctor(Doctor doctor);
    Optional<Appointment> findAppointmentByPatient(Patient patient);
}
