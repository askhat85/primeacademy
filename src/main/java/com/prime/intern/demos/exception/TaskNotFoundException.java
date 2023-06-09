package com.prime.intern.demos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends ResponseStatusException {

    public TaskNotFoundException(HttpStatus status, String message) {
        super(status, message);
    }
}
