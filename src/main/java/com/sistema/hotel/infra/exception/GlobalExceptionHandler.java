package com.sistema.hotel.infra.exception;


import jakarta.servlet.http.HttpServletRequest;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleGeneric(
                Exception ex, HttpServletRequest request) {
                ErrorResponse error = new ErrorResponse(
                        500,
                        "Internal Server Error. Contact suport team",
                        request.getRequestURI()
                );
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleNotFound(
                Exception ex, HttpServletRequest request) {

                ErrorResponse error = new ErrorResponse(
                        404,
                        ex.getMessage(),
                        request.getRequestURI()
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
                var errors = ex.getFieldErrors().stream().map(ResponseErrorException::new).toList();
                var response = new ErrorResponse(
                        400,
                        errors.toString(),
                        request.getRequestURI()
                );
            return ResponseEntity.badRequest().body(response);
        }
}
