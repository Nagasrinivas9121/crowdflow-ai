package com.crowdflow.service.impl;

import com.crowdflow.constants.AppConstants;
import com.crowdflow.dto.QueueRequest;
import com.crowdflow.dto.WaitTimeResponse;
import com.crowdflow.service.WaitTimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WaitTimeServiceImpl implements WaitTimeService {

    @Override
    public WaitTimeResponse calculateWaitTime(QueueRequest request) {
        log.info("Calculating wait time for {} people, {} service time, {} counters", 
                request.getPeople(), request.getServiceTime(), request.getCounters());
                
        if (request.getCounters() <= 0) {
            throw new IllegalArgumentException("Number of active counters must be greater than zero.");
        }
        
        double waitTime = (double) (request.getPeople() * request.getServiceTime()) / request.getCounters();
        String recommendation = determineRecommendation(waitTime);
        
        log.info("Calculated wait time: {} min. Recommendation: {}", waitTime, recommendation);
        return new WaitTimeResponse(waitTime, recommendation);
    }

    private String determineRecommendation(double waitTime) {
        if (waitTime < AppConstants.FAST_THRESHOLD) {
            return AppConstants.REC_FAST;
        } else if (waitTime <= AppConstants.MEDIUM_THRESHOLD) {
            return AppConstants.REC_MODERATE;
        }
        return AppConstants.REC_SLOW;
    }
}
