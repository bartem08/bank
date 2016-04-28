package com.bank.exception;

import org.springframework.validation.Errors;

public class AccessLockedException extends RuntimeException {

    public AccessLockedException(String message) {
        super(message);
    }

}
