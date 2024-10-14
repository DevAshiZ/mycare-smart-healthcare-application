package com.csse.mycare.common.exceptions;

public class UserRegistrationException extends Exception {
    public UserRegistrationException(String s) {
        super("User registration failed");
    }
}
