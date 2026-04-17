package com.crowdflow.service;

import com.crowdflow.dto.AssistantRequest;
import com.crowdflow.dto.AssistantResponse;

/**
 * Service interface for handling AI assistant queries.
 */
public interface AssistantService {

    /**
     * Processes a user query and returns an intelligent response.
     *
     * @param request The chat query
     * @return AssistantResponse containing the reply
     */
    AssistantResponse getAssistantResponse(AssistantRequest request);
}
