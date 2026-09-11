package com.mateo.smart_store_assistant.ai;

import com.mateo.smart_store_assistant.model.Product;
import com.mateo.smart_store_assistant.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that orchestrates the AI agent logic.
 * Fetches real inventory data from the database and combines it
 * with the user's question to generate a context-aware AI response.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
@Service
public class AiAgentService {

    private final GroqService groqService;
    private final ProductService productService;

    /**
     * Constructs the AiAgentService with required dependencies.
     *
     * @param groqService    service for communicating with the Groq AI API
     * @param productService service for retrieving product data from the database
     */
    public AiAgentService(GroqService groqService, ProductService productService){
        this.groqService = groqService;
        this.productService = productService;
    }

    /**
     * Processes a natural language query about the inventory.
     * Retrieves current inventory data, builds a context-aware prompt,
     * and returns an AI-generated response.
     *
     * @param userQuestion the natural language question from the user
     * @return AI-generated response based on real inventory data
     */
    public String processQuery(String userQuestion){
        List<Product> products = productService.getAllProducts();

        StringBuilder inventoryContext = new StringBuilder();
        inventoryContext.append("Current inventory data:\n");

        for (Product product : products) {
            inventoryContext.append(String.format(
                    "- %s | Category: %s | Stock: %d units | Price: %.2f | Expiry: %s\n",
                    product.getName(),
                    product.getCategory(),
                    product.getStock(),
                    product.getPrice(),
                    product.getExpiryDate() != null ? product.getExpiryDate().toString() : "N/A"
            ));
        }

        String fullPromt = inventoryContext.toString() + "\nUser question: " + userQuestion;

        return groqService.sendPrompt(fullPromt);
    }
}
