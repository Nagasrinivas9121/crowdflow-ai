package com.crowdflow.dto;

import lombok.Data;

@Data
public class QueueRequest {
    private int people;
    private int serviceTime;
    private int counters;
}
