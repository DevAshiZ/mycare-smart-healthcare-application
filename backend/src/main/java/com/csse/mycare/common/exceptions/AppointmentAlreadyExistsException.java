package com.csse.mycare.common.exceptions;

public class AppointmentAlreadyExistsException extends Exception {
    public AppointmentAlreadyExistsException() {
        super("Appointment already exists");
    }
}
