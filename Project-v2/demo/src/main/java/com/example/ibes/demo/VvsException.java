package com.example.ibes.demo;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class VvsException extends RuntimeException {

    @Getter
    private final HttpStatus httpStatus;

    public VvsException(HttpStatus status) {
        this(status, null, null);
    }

    public VvsException(HttpStatus status, String reason) {
        this(status, reason, null);
    }

    public VvsException(HttpStatus status, String reason, Throwable cause) {
        super(reason, cause);
        this.httpStatus = status;
    }
}
