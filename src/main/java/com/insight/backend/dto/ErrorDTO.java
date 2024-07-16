package com.insight.backend.dto;

/**
 * Data Transfer Object to represent error responses.
 */
public class ErrorDTO {

    /**
     * The error message to be conveyed to the client.
     */
    private String message;

    /**
     * Constructor to initialize the error message.
     * @param message the error message.
     */
    public ErrorDTO(String message) {
        this.message = message;
    }

    /**
     * Getter for the error message.
     * @return the error message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter for the error message.
     * @param message the error message.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
