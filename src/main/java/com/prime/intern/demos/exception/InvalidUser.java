package com.prime.intern.demos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidUser extends IllegalArgumentException {
    public InvalidUser(String message) {
        super(message);
    }
}
