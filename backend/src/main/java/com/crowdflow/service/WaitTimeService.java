package com.crowdflow.service;

import com.crowdflow.dto.QueueRequest;
import com.crowdflow.dto.WaitTimeResponse;

/**
 * Service interface for calculating wait times.
 */
public interface WaitTimeService {

    /**
     * Calculates the estimated wait time for a queue.
     *
     * @param request The queue details
     * @return WaitTimeResponse containing time and recommendation
     */
    WaitTimeResponse calculateWaitTime(QueueRequest request);
}
