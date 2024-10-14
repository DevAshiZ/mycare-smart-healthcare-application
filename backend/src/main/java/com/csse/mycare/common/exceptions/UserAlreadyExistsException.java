package com.csse.mycare.common.exceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String s) {
        super("User already exists");
    }
}
