package com.csse.mycare.masterservice.service;

import com.csse.mycare.common.exceptions.PaymentAlreadyMadeException;
import com.csse.mycare.masterservice.dao.CardPayment;
import com.csse.mycare.masterservice.dao.CashPayment;
import com.csse.mycare.masterservice.repository.CardPaymentRepository;
import com.csse.mycare.masterservice.repository.CashPaymentRepository;
import com.csse.mycare.masterservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    PaymentRepository paymentRepository;
    CardPaymentRepository cardPaymentRepository;
    CashPaymentRepository cashPaymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, CardPaymentRepository cardPaymentRepository, CashPaymentRepository cashPaymentRepository) {
        this.paymentRepository = paymentRepository;
        this.cardPaymentRepository = cardPaymentRepository;
        this.cashPaymentRepository = cashPaymentRepository;
    }

    @Override
    public CardPayment createCardPayment(CardPayment cardPayment) throws PaymentAlreadyMadeException {

        if(paymentRepository.existsByIsPaidIsTrueAndAppointmentId(cardPayment.getAppointmentId())){
            throw new PaymentAlreadyMadeException();
        }
        return cardPaymentRepository.save(cardPayment);
    }

    @Override
    public CashPayment createCashPayment(CashPayment cashPayment) throws PaymentAlreadyMadeException {

        if(paymentRepository.existsByIsPaidIsTrueAndAppointmentId(cashPayment.getAppointmentId())){
            throw new PaymentAlreadyMadeException();
        }
        return cashPaymentRepository.save(cashPayment);
    }
}
