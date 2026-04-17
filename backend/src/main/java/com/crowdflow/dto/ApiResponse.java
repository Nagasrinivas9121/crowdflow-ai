package com.crowdflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Generic API response wrapper for standardized responses.
 *
 * @param <T> The type of the data payload
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    /**
     * Helper method to build a success response.
     *
     * @param data The payload to return
     * @param message A success message
     * @param <T> The payload type
     * @return A constructed ApiResponse object
     */
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>("SUCCESS", message, data, LocalDateTime.now());
    }

    /**
     * Helper method to build an error response.
     *
     * @param message The error message
     * @return A constructed ApiResponse object
     */
    public static ApiResponse<Void> error(String message) {
        return new ApiResponse<>("ERROR", message, null, LocalDateTime.now());
    }
}
