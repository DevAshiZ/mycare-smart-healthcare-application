package com.csse.mycare.common.exceptions;

public class ReferedDoctorNotFoundException extends Exception {
    public ReferedDoctorNotFoundException() {
        super("Referred Doctor not found");
    }
}
