package com.crowdflow.service;

import com.crowdflow.dto.AssistantRequest;
import com.crowdflow.dto.AssistantResponse;
import org.springframework.stereotype.Service;

@Service
public class AssistantService {

    public AssistantResponse getAssistantResponse(AssistantRequest request) {
        String query = request.getQuery() != null ? request.getQuery().toLowerCase() : "";
        
        String response;
        if (query.contains("fastest gate") || query.contains("where should i enter")) {
            response = "Gate B currently has the lowest crowd density. It is recommended to enter through Gate B.";
        } else if (query.contains("least crowded area") || query.contains("least crowded")) {
            response = "Gate B currently has the lowest crowd density.";
        } else if (query.contains("best food stall") || query.contains("food")) {
            response = "Food Court B has the shortest waiting time.";
        } else if (query.contains("nearest exit") || query.contains("exit")) {
            response = "Exit Gate 2 is least crowded right now.";
        } else {
            response = "I can help you find the fastest gate, least crowded areas, best food stalls, or nearest exits. Try asking 'fastest gate' or 'least crowded area'.";
        }
        
        return new AssistantResponse(response);
    }
}
