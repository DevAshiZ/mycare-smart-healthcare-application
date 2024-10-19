package com.csse.mycare.masterservice.repository;


import com.csse.mycare.masterservice.dao.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    boolean existsByIsPaidIsTrueAndAppointmentId(Integer appointmentId);
    List<Payment> getAllByUserId(Integer userId);
}




