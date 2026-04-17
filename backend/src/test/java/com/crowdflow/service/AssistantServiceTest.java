package com.crowdflow.service;

import com.crowdflow.dto.AssistantRequest;
import com.crowdflow.dto.AssistantResponse;
import com.crowdflow.service.impl.AssistantServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssistantServiceTest {

    private AssistantServiceImpl assistantService;

    @BeforeEach
    void setUp() {
        assistantService = new AssistantServiceImpl();
    }

    @Test
    void testGetAssistantResponse_FastestGate() {
        AssistantRequest req = new AssistantRequest();
        req.setQuery("Which is the fastest gate?");
        
        AssistantResponse resp = assistantService.getAssistantResponse(req);
        
        assertTrue(resp.getResponse().contains("Gate B currently has the lowest crowd density"));
    }

    @Test
    void testGetAssistantResponse_FoodStall() {
        AssistantRequest req = new AssistantRequest();
        req.setQuery("I am hungry, where is the best food stall?");
        
        AssistantResponse resp = assistantService.getAssistantResponse(req);
        
        assertTrue(resp.getResponse().contains("Food Court B has the shortest waiting time"));
    }

    @Test
    void testGetAssistantResponse_UnknownQuery() {
        AssistantRequest req = new AssistantRequest();
        req.setQuery("hello world");
        
        AssistantResponse resp = assistantService.getAssistantResponse(req);
        
        assertTrue(resp.getResponse().contains("I can help you find"));
    }

    @Test
    void testGetAssistantResponse_NullQuery() {
        AssistantRequest req = new AssistantRequest();
        req.setQuery(null);
        
        AssistantResponse resp = assistantService.getAssistantResponse(req);
        
        assertTrue(resp.getResponse().contains("I can help you find"));
    }
}
