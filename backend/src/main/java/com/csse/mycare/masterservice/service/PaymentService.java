package com.csse.mycare.masterservice.service;


import com.csse.mycare.common.exceptions.PaymentAlreadyMadeException;
import com.csse.mycare.masterservice.dao.CardPayment;
import com.csse.mycare.masterservice.dao.CashPayment;
import com.csse.mycare.masterservice.dao.Payment;
import com.csse.mycare.patient.dto.PaymentResponse;

import java.util.List;

public interface PaymentService {

    CardPayment createCardPayment(CardPayment cardPayment) throws PaymentAlreadyMadeException;
    CashPayment createCashPayment(CashPayment cashPayment) throws PaymentAlreadyMadeException;
    List<PaymentResponse> getAllPaymentsByUserId(Integer userId);
}
