package com.crowdflow.controller;

import com.crowdflow.service.GoogleCloudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api/cloud-info")
public class CloudInfoController {

    private final GoogleCloudService googleCloudService;

    public CloudInfoController(GoogleCloudService googleCloudService) {
        this.googleCloudService = googleCloudService;
    }

    @GetMapping
    public Map<String, Object> getCloudInfo() {
        log.info("Retrieving cloud environment info");
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Google Cloud environment detected");
        response.put("projectId", googleCloudService.getProjectId());
        response.put("service", "CrowdFlow AI");
        return response;
    }
}
