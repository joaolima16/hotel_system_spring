package com.sistema.hotel.infra.exception;

import org.springframework.validation.FieldError;

public record ResponseErrorException(String field, String messsage) {
    public ResponseErrorException(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
