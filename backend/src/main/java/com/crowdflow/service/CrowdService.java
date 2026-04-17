package com.crowdflow.service;

import com.crowdflow.model.CrowdZone;
import com.crowdflow.repository.CrowdZoneRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CrowdService {

    private final CrowdZoneRepository repository;
    private final Random random;

    @Autowired
    public CrowdService(CrowdZoneRepository repository) {
        this.repository = repository;
        this.random = new Random();
    }

    @PostConstruct
    public void init() {
        if (repository.count() == 0) {
            repository.save(new CrowdZone("Gate A", 30, 0, ""));
            repository.save(new CrowdZone("Gate B", 15, 0, ""));
            repository.save(new CrowdZone("Food Court", 60, 0, ""));
            repository.save(new CrowdZone("Exit", 20, 0, ""));
            repository.save(new CrowdZone("Parking", 45, 0, ""));
        }
    }

    public List<CrowdZone> getCrowdPrediction() {
        List<CrowdZone> zones = repository.findAll();
        
        for (CrowdZone zone : zones) {
            int current = zone.getCurrentDensity();
            int change = random.nextInt(21) - 5;
            int predicted = current + change;
            
            if (predicted < 0) predicted = 0;
            if (predicted > 100) predicted = 100;
            
            String riskLevel;
            if (predicted < 40) {
                riskLevel = "LOW";
            } else if (predicted <= 75) {
                riskLevel = "MEDIUM";
            } else {
                riskLevel = "HIGH";
            }
            
            zone.setCurrentDensity(predicted);
            zone.setPredictedDensity(predicted);
            zone.setRiskLevel(riskLevel);
        }
        
        return repository.saveAll(zones);
    }
}
