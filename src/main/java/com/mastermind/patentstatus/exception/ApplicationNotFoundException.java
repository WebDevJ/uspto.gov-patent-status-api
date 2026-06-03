package com.mastermind.patentstatus.exception;

public class ApplicationNotFoundException extends RuntimeException {

    public ApplicationNotFoundException(String applicationNumber) {
        super("Application record not found for application number: " + applicationNumber);
    }
}