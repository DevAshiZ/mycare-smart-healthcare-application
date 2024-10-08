package com.csse.mycare.masterservice.repository;

import com.csse.mycare.masterservice.dao.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}
