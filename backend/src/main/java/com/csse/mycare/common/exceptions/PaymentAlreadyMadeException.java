package com.csse.mycare.common.exceptions;

public class PaymentAlreadyMadeException extends Exception{
    public PaymentAlreadyMadeException() {
        super("Payment is already made for this appointment.");
    }
}
