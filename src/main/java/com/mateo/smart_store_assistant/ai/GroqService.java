package com.mateo.smart_store_assistant.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Service responsible for communicating with the Groq AI API.
 * Sends prompts and returns AI-generated responses in natural language.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
@Service
public class GroqService {

    @Value("${groq.api.key}")
    private String apiKey;

    @Value("${groq.api.url}")
    private String apiUrl;

    @Value("${groq.model}")
    private String model;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Sends a prompt to the Groq API and returns the AI-generated response.
     *
     * @param prompt the full prompt including inventory context and user question
     * @return the AI-generated response as a plain text string
     */
    public String sendPrompt(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> requestBody = Map.of(
                "model", model,
                "messages", List.of(
                        Map.of(
                                "role", "system",
                                "content", "You are a helpful inventory assistant for a minimarket. " +
                                        "Answer questions about products, stock levels, prices, and expiry dates. " +
                                        "Be concise and helpful. Always respond in the same language the user uses."
                        ),
                        Map.of(
                                "role", "user",
                                "content", prompt
                        )
                ),
                "max_tokens", 500
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, Map.class);

        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
        return (String) message.get("content");
    }
}










