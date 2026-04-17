package com.crowdflow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrowdZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private int currentDensity;
    private int predictedDensity;
    private String riskLevel;

    public CrowdZone(String name, int currentDensity, int predictedDensity, String riskLevel) {
        this.name = name;
        this.currentDensity = currentDensity;
        this.predictedDensity = predictedDensity;
        this.riskLevel = riskLevel;
    }
}
