package com.crowdflow.controller;

import com.crowdflow.dto.ApiResponse;
import com.crowdflow.dto.AssistantRequest;
import com.crowdflow.dto.AssistantResponse;
import com.crowdflow.service.AssistantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing AI assistant endpoints.
 */
@Slf4j
@RestController
@RequestMapping("/api/assistant")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AssistantController {

    private final AssistantService assistantService;

    /**
     * Processes a user chat query and returns an intelligent response.
     * 
     * @param request Validated AssistantRequest body
     * @return ApiResponse containing the AssistantResponse
     */
    @PostMapping
    public ApiResponse<AssistantResponse> getAssistantResponse(@Valid @RequestBody AssistantRequest request) {
        log.info("Received request for AI assistant");
        AssistantResponse response = assistantService.getAssistantResponse(request);
        return ApiResponse.success(response, "Assistant response generated successfully");
    }
}
