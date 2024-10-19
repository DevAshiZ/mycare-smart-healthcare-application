package com.csse.mycare.masterservice.repository;


import com.csse.mycare.masterservice.dao.CardPayment;
import com.csse.mycare.masterservice.dao.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Boolean findByIsPaidTrueAndAppointmentId(Long appointmentId);
}




