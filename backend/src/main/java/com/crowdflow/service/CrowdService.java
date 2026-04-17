package com.crowdflow.service;

import com.crowdflow.model.CrowdZone;

import java.util.List;

/**
 * Service interface for crowd density predictions.
 */
public interface CrowdService {
    
    /**
     * Retrieves the current and predicted crowd density for all zones.
     * 
     * @return List of predicted CrowdZone models
     */
    List<CrowdZone> getCrowdPrediction();
}
