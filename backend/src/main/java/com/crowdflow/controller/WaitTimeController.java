package com.crowdflow.controller;

import com.crowdflow.dto.QueueRequest;
import com.crowdflow.dto.WaitTimeResponse;
import com.crowdflow.service.WaitTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wait-time")
@CrossOrigin(origins = "*")
public class WaitTimeController {

    private final WaitTimeService waitTimeService;

    @Autowired
    public WaitTimeController(WaitTimeService waitTimeService) {
        this.waitTimeService = waitTimeService;
    }

    @PostMapping
    public WaitTimeResponse getWaitTime(@RequestBody QueueRequest request) {
        return waitTimeService.calculateWaitTime(request);
    }
}
