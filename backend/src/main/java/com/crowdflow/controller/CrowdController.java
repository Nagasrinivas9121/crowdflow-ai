package com.crowdflow.controller;

import com.crowdflow.model.CrowdZone;
import com.crowdflow.service.CrowdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/crowd")
@CrossOrigin(origins = "*")
public class CrowdController {

    private final CrowdService crowdService;

    @Autowired
    public CrowdController(CrowdService crowdService) {
        this.crowdService = crowdService;
    }

    @GetMapping
    public List<CrowdZone> getCrowd() {
        return crowdService.getCrowdPrediction();
    }
}
