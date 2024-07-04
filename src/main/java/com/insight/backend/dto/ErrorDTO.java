package com.insight.backend.dto;

import jakarta.validation.constraints.*;

/**
 * Data Transfer Object (DTO) for representing error responses.
 * <p>
 * This class encapsulates error messages used in responses to indicate
 * issues or problems encountered during processing. It includes validation
 * constraints to ensure the error message is not blank and within a specified
 * length.
 * </p>
 * 
 * @author Abolfazl
 * @version 1.0
 * @since 04.07.2024
 */
public class ErrorDTO {

    /**
     * Error message.
     * <p>
     * This field must not be blank and should be up to 255 characters long. 
     * It provides a description of the error encountered.
     * </p>
     * 
     * @see jakarta.validation.constraints.NotBlank
     * @see jakarta.validation.constraints.Size
     */
    @NotBlank(message = "Error message cannot be blank")
    @Size(max = 255, message = "Error message should be up to 255 characters")
    private String error;

    /**
     * No-argument constructor.
     * <p>
     * Required for frameworks that use reflection or deserialization to create 
     * instances of this class. Initializes the object without setting any fields.
     * </p>
     */
    public ErrorDTO() {}

    /**
     * Constructs an {@code ErrorDTO} with the specified error message.
     * 
     * @param error the error message to set
     */
    public ErrorDTO(String error) {
        this.error = error;
    }

    /**
     * Returns the error message.
     * 
     * @return the error message
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the error message.
     * 
     * @param error the error message to set
     */
    public void setError(String error) {
        this.error = error;
    }
}
