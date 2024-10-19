package com.csse.mycare.common.exceptions;

public class PaymentFailedException extends Exception {
    public PaymentFailedException() {
        super("Payment failed.");
    }
}
