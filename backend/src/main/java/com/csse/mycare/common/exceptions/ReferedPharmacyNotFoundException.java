package com.csse.mycare.common.exceptions;

public class ReferedPharmacyNotFoundException extends Exception {
    public ReferedPharmacyNotFoundException() {
        super("Referred pharmacy not found");
    }
}
