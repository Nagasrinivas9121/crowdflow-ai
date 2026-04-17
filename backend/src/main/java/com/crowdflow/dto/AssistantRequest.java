package com.crowdflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for incoming assistant chat queries.
 */
@Data
public class AssistantRequest {
    
    @NotBlank(message = "Query cannot be blank")
    @Size(min = 2, max = 500, message = "Query must be between 2 and 500 characters")
    private String query;
}
