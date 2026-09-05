package com.mateo.smart_store_assistant.controller;

import com.mateo.smart_store_assistant.model.Product;
import com.mateo.smart_store_assistant.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller for managing products in the store inventory.
 * Exposes endpoints for CRUD operations and expiry alerts.
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Retrieves all products from the inventory.
     *
     * @return list of all products with HTTP 200
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * Retrieves a single product by its ID.
     *
     * @param id the product ID
     * @return the product with HTTP 200, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    /**
     * Creates a new product in the inventory.
     *
     * @param product the product data from the request body
     * @return the created product with HTTP 200
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    /**
     * Updates an existing product by ID.
     *
     * @param id the ID of the product to update
     * @param product the new product data
     * @return the updated product with HTTP 200
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     * @return HTTP 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Returns all products expiring within the next N days.
     *
     * @param days number of days ahead to check (default: 7)
     * @return list of expiring products with HTTP 200
     */
    @GetMapping("/expiring")
    public ResponseEntity<List<Product>> getExpiringProducts(
            @RequestParam(defaultValue = "7") int days) {
        return ResponseEntity.ok(productService.getExpiringProducts(days));
    }
}
