package com.crowdflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WaitTimeResponse {
    private double waitTime;
    private String recommendation;
}
