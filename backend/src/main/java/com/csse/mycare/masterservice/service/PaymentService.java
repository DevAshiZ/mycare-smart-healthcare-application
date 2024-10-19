package com.csse.mycare.masterservice.service;


import com.csse.mycare.common.exceptions.PaymentAlreadyMadeException;
import com.csse.mycare.masterservice.dao.CardPayment;
import com.csse.mycare.masterservice.dao.CashPayment;

public interface PaymentService {

    CardPayment createCardPayment(CardPayment cardPayment) throws PaymentAlreadyMadeException;
    CashPayment createCashPayment(CashPayment cashPayment) throws PaymentAlreadyMadeException;
}
