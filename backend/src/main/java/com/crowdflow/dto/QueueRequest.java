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
    
    @NotNull(message = "Number of people cannot be null")
    @Min(value = 0, message = "Number of people cannot be negative")
    @Max(value = 100000, message = "Number of people exceeds maximum supported capacity")
    private Integer people;
    
    @NotNull(message = "Service time cannot be null")
    @Min(value = 1, message = "Service time must be at least 1 minute")
    @Max(value = 60, message = "Service time cannot exceed 60 minutes")
    private Integer serviceTime;
    
    @NotNull(message = "Number of counters cannot be null")
    @Min(value = 1, message = "There must be at least 1 active counter")
    @Max(value = 1000, message = "Number of counters cannot exceed 1000")
    private Integer counters;
}
