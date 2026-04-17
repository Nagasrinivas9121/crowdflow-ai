package com.crowdflow.service.impl;

import com.crowdflow.constants.AppConstants;
import com.crowdflow.model.CrowdZone;
import com.crowdflow.repository.CrowdZoneRepository;
import com.crowdflow.service.CrowdService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrowdServiceImpl implements CrowdService {

    private final CrowdZoneRepository repository;
    private final SecureRandom random = new SecureRandom();

    @PostConstruct
    public void init() {
        if (repository.count() == 0) {
            log.info("Initializing default crowd zones...");
            repository.save(new CrowdZone("Gate A", 30, 0, ""));
            repository.save(new CrowdZone("Gate B", 15, 0, ""));
            repository.save(new CrowdZone("Food Court", 60, 0, ""));
            repository.save(new CrowdZone("Exit", 20, 0, ""));
            repository.save(new CrowdZone("Parking", 45, 0, ""));
        }
    }

    @Override
    public List<CrowdZone> getCrowdPrediction() {
        log.info("Fetching crowd predictions...");
        List<CrowdZone> zones = repository.findAll();
        
        for (CrowdZone zone : zones) {
            int current = zone.getCurrentDensity();
            int change = random.nextInt(21) - 5;
            int predicted = Math.max(AppConstants.MIN_DENSITY, Math.min(current + change, AppConstants.MAX_DENSITY));
            
            zone.setCurrentDensity(predicted);
            zone.setPredictedDensity(predicted);
            zone.setRiskLevel(determineRiskLevel(predicted));
        }
        
        return repository.saveAll(zones);
    }

    private String determineRiskLevel(int density) {
        if (density < 40) {
            return AppConstants.RISK_LOW;
        } else if (density <= 75) {
            return AppConstants.RISK_MEDIUM;
        }
        return AppConstants.RISK_HIGH;
    }
}
