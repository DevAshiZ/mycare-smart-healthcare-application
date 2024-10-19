package com.csse.mycare.masterservice.repository;

import com.csse.mycare.masterservice.dao.CashPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashPaymentRepository extends JpaRepository<CashPayment, Long> {
}
