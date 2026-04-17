package com.crowdflow.service;

import com.crowdflow.dto.QueueRequest;
import com.crowdflow.dto.WaitTimeResponse;
import org.springframework.stereotype.Service;

@Service
public class WaitTimeService {

    public WaitTimeResponse calculateWaitTime(QueueRequest request) {
        if (request.getCounters() <= 0) {
            return new WaitTimeResponse(0, "Invalid counters");
        }
        
        double waitTime = (double) (request.getPeople() * request.getServiceTime()) / request.getCounters();
        
        String recommendation;
        if (waitTime < 10) {
            recommendation = "FAST";
        } else if (waitTime <= 20) {
            recommendation = "MODERATE";
        } else {
            recommendation = "SLOW";
        }
        
        return new WaitTimeResponse(waitTime, recommendation);
    }
}
