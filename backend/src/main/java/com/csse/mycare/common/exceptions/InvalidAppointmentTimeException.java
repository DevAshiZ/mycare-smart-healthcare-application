package com.csse.mycare.common.exceptions;

public class InvalidAppointmentTimeException extends Exception {
    public InvalidAppointmentTimeException() {
        super("Invalid or unavailable appointment time");
    }
}
