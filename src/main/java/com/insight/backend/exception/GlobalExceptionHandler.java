package com.insight.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.insight.backend.dto.ErrorDTO;

/**
 * Global exception handler for the application.
 * Handles exceptions thrown by controllers globally.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles MethodArgumentNotValidException thrown when validation fails for request body.
     * Returns a ResponseEntity with HTTP status 400 (BAD_REQUEST) and an ErrorDTO containing the validation error message.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        ErrorDTO errorDTO = new ErrorDTO(errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    // Add more exception handlers as needed

}
