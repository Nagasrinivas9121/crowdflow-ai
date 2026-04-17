package com.crowdflow.controller;

import com.crowdflow.dto.ApiResponse;
import com.crowdflow.model.CrowdZone;
import com.crowdflow.service.CrowdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for managing crowd density endpoints.
 */
@Slf4j
@RestController
@RequestMapping("/api/crowd")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CrowdController {

    private final CrowdService crowdService;

    /**
     * Retrieves current and predicted crowd density.
     * 
     * @return ApiResponse containing the list of CrowdZone data
     */
    @GetMapping
    public ApiResponse<List<CrowdZone>> getCrowd() {
        log.info("Received request to get crowd predictions");
        List<CrowdZone> predictions = crowdService.getCrowdPrediction();
        return ApiResponse.success(predictions, "Crowd predictions fetched successfully");
    }
}
