package com.crowdflow.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO for incoming queue wait time calculation requests.
 */
@Data
public class QueueRequest {
    
    @NotNull(message="value is required")
    @Min(value=1,message="must be greater than zero")
    private Integer people;
    
    @NotNull(message="value is required")
    @Min(value=1,message="must be greater than zero")
    private Integer serviceTime;
    
    @NotNull(message="value is required")
    @Min(value=1,message="must be greater than zero")
    private Integer counters;
}
