package com.crowdflow.controller;

import com.crowdflow.dto.ApiResponse;
import com.crowdflow.dto.QueueRequest;
import com.crowdflow.dto.WaitTimeResponse;
import com.crowdflow.service.WaitTimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing wait time endpoints.
 */
@Slf4j
@RestController
@RequestMapping("/api/wait-time")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class WaitTimeController {

    private final WaitTimeService waitTimeService;

    /**
     * Calculates the estimated wait time for a queue.
     * 
     * @param request Validated QueueRequest body
     * @return ApiResponse containing the WaitTimeResponse
     */
    @PostMapping
    public ApiResponse<WaitTimeResponse> getWaitTime(@Valid @RequestBody QueueRequest request) {
        log.info("Received request to calculate wait time");
        WaitTimeResponse response = waitTimeService.calculateWaitTime(request);
        return ApiResponse.success(response, "Wait time calculated successfully");
    }
}
