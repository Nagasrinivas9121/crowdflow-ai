package com.crowdflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for incoming assistant chat queries.
 */
@Data
public class AssistantRequest {
    
    @NotBlank(message="text required")
    @Size(max=200,message="text too long")
    private String query;
}
