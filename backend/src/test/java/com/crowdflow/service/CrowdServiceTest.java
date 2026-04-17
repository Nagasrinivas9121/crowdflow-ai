package com.crowdflow.service;

import com.crowdflow.constants.AppConstants;
import com.crowdflow.model.CrowdZone;
import com.crowdflow.repository.CrowdZoneRepository;
import com.crowdflow.service.impl.CrowdServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class CrowdServiceTest {

    @Mock
    private CrowdZoneRepository repository;

    @InjectMocks
    private CrowdServiceImpl crowdService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInit_WhenEmpty() {
        when(repository.count()).thenReturn(0L);
        crowdService.init();
        verify(repository, times(5)).save(any(CrowdZone.class));
    }

    @Test
    void testInit_WhenNotEmpty() {
        when(repository.count()).thenReturn(5L);
        crowdService.init();
        verify(repository, never()).save(any(CrowdZone.class));
    }

    @Test
    void testGetCrowdPrediction() {
        List<CrowdZone> mockZones = new ArrayList<>();
        mockZones.add(new CrowdZone("Gate A", 50, 0, ""));
        mockZones.add(new CrowdZone("Gate B", 10, 0, ""));

        when(repository.findAll()).thenReturn(mockZones);
        when(repository.saveAll(anyList())).thenReturn(mockZones);

        List<CrowdZone> result = crowdService.getCrowdPrediction();

        assertEquals(2, result.size());
        
        for (CrowdZone zone : result) {
            assertTrue(zone.getPredictedDensity() >= AppConstants.MIN_DENSITY);
            assertTrue(zone.getPredictedDensity() <= AppConstants.MAX_DENSITY);
            assertNotNull(zone.getRiskLevel());
            assertTrue(zone.getRiskLevel().equals(AppConstants.RISK_LOW) || 
                       zone.getRiskLevel().equals(AppConstants.RISK_MEDIUM) || 
                       zone.getRiskLevel().equals(AppConstants.RISK_HIGH));
        }

        verify(repository, times(1)).saveAll(mockZones);
    }
}
