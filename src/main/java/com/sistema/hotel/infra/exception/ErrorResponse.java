package com.sistema.hotel.infra.exception;

public record ErrorResponse(int status, String message, String path) {
}
