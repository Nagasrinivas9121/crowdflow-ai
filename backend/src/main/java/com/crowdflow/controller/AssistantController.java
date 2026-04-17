package com.crowdflow.controller;

import com.crowdflow.dto.AssistantRequest;
import com.crowdflow.dto.AssistantResponse;
import com.crowdflow.service.AssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assistant")
@CrossOrigin(origins = "*")
public class AssistantController {

    private final AssistantService assistantService;

    @Autowired
    public AssistantController(AssistantService assistantService) {
        this.assistantService = assistantService;
    }

    @PostMapping
    public AssistantResponse getAssistantResponse(@RequestBody AssistantRequest request) {
        return assistantService.getAssistantResponse(request);
    }
}
