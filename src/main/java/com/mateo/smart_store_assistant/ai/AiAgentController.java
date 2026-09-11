package com.mateo.smart_store_assistant.ai;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST controller for AI agent interactions.
 * Provides an endpoint for natural language queries about the inventory.
 * Requires a valid JWT token for access.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
@RestController
@RequestMapping("/ai")
public class AiAgentController {

    private final AiAgentService aiAgentService;

    /**
     * Constructs the AiAgentController with required dependencies.
     *
     * @param aiAgentService service that orchestrates AI query processing
     */
    public AiAgentController(AiAgentService aiAgentService){
        this.aiAgentService = aiAgentService;
    }

    /**
     * Processes a natural language query about the inventory.
     * Requires a valid Bearer JWT token in the Authorization header.
     *
     * @param request map containing the "question" field
     * @return AI-generated response based on real inventory data
     */
    @PostMapping("/query")
    public ResponseEntity<Map<String, String>> query(@RequestBody Map<String, String> request) {
        String question = request.get("question");

        if (question == null || question.isBlank()){
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Question cannot be empty"));
        }

        String response = aiAgentService.processQuery(question);
        return ResponseEntity.ok(Map.of("response", response));
    }
}













