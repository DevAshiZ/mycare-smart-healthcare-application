package com.csse.mycare.masterservice.service;

import com.csse.mycare.common.exceptions.PaymentAlreadyMadeException;
import com.csse.mycare.masterservice.dao.CardPayment;
import com.csse.mycare.masterservice.dao.CashPayment;
import com.csse.mycare.masterservice.dao.Payment;
import com.csse.mycare.masterservice.repository.CardPaymentRepository;
import com.csse.mycare.masterservice.repository.CashPaymentRepository;
import com.csse.mycare.masterservice.repository.PaymentRepository;
import com.csse.mycare.patient.dto.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<PaymentResponse> getAllPaymentsByUserId(Integer userId) {
        List<Payment> payments = paymentRepository.getAllByUserId(userId);

        if(payments != null){
            return payments.stream().map(payment -> {
                PaymentResponse paymentResponse = new PaymentResponse();
                paymentResponse.setTransactionId(String.valueOf(payment.getId()));
                paymentResponse.setPaymentMethod(String.valueOf(payment.getPaymentMethod()));
                paymentResponse.setAppointmentID(String.valueOf(payment.getAppointmentId()));
                paymentResponse.setPaymentDateTime(payment.getPaymentDateTime());
                paymentResponse.setIsPaid(payment.getIsPaid());
                paymentResponse.setPaymentAmount(payment.getAmount());

                return paymentResponse;
            }).collect(Collectors.toList());
        }

        return null;
    }
}
