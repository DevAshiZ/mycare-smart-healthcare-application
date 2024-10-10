package com.csse.mycare.common.exceptions;

public class InvalidUpdateUserException extends Exception {
    public InvalidUpdateUserException() {
        super("Attempted to update a user that does not exist");
    }
}
