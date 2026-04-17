package com.crowdflow.service;

import com.google.cloud.ServiceOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GoogleCloudService {
    
    public String getProjectId() {
        String projectId = ServiceOptions.getDefaultProjectId();
        log.info("Detected Google Cloud Project ID: {}", projectId);
        return projectId;
    }
}
