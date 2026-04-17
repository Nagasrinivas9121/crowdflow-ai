package com.crowdflow.service;

import com.crowdflow.constants.AppConstants;
import com.crowdflow.dto.QueueRequest;
import com.crowdflow.dto.WaitTimeResponse;
import com.crowdflow.service.impl.WaitTimeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaitTimeServiceTest {

    private WaitTimeServiceImpl waitTimeService;

    @BeforeEach
    void setUp() {
        waitTimeService = new WaitTimeServiceImpl();
    }

    @Test
    void testCalculateWaitTime_Fast() {
        QueueRequest req = new QueueRequest();
        req.setPeople(10);
        req.setServiceTime(2);
        req.setCounters(4);

        WaitTimeResponse resp = waitTimeService.calculateWaitTime(req);

        assertEquals(5.0, resp.getWaitTime());
        assertEquals(AppConstants.REC_FAST, resp.getRecommendation());
    }

    @Test
    void testCalculateWaitTime_Moderate() {
        QueueRequest req = new QueueRequest();
        req.setPeople(30);
        req.setServiceTime(2);
        req.setCounters(4);

        WaitTimeResponse resp = waitTimeService.calculateWaitTime(req);

        assertEquals(15.0, resp.getWaitTime());
        assertEquals(AppConstants.REC_MODERATE, resp.getRecommendation());
    }

    @Test
    void testCalculateWaitTime_Slow() {
        QueueRequest req = new QueueRequest();
        req.setPeople(100);
        req.setServiceTime(2);
        req.setCounters(4);

        WaitTimeResponse resp = waitTimeService.calculateWaitTime(req);

        assertEquals(50.0, resp.getWaitTime());
        assertEquals(AppConstants.REC_SLOW, resp.getRecommendation());
    }

    @Test
    void testCalculateWaitTime_InvalidCounters_ThrowsException() {
        QueueRequest req = new QueueRequest();
        req.setPeople(100);
        req.setServiceTime(2);
        req.setCounters(0);

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> waitTimeService.calculateWaitTime(req)
        );
        assertTrue(thrown.getMessage().contains("greater than zero"));
    }
}
