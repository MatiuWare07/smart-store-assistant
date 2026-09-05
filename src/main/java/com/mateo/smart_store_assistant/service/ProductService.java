package com.mateo.smart_store_assistant.service;

import com.mateo.smart_store_assistant.model.Product;
import com.mateo.smart_store_assistant.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

/**
 * Service layer for product business logic.
 * Handles all operations related to product management and expiry alerts.
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Retrieves all products from the database.
     *
     * @return list of all products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the product ID
     * @return the product if found
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    /**
     * Creates and saves a new product.
     *
     * @param product the product to save
     * @return the saved product
     */
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Updates an existing product by ID.
     *
     * @param id the ID of the product to update
     * @param updatedProduct the new product data
     * @return the updated product
     */
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = getProductById(id);
        existing.setName(updatedProduct.getName());
        existing.setCategory(updatedProduct.getCategory());
        existing.setStock(updatedProduct.getStock());
        existing.setPrice(updatedProduct.getPrice());
        existing.setExpiryDate(updatedProduct.getExpiryDate());
        return productRepository.save(existing);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * Returns all products expiring within the next given number of days.
     *
     * @param days number of days ahead to check
     * @return list of products expiring soon
     */
    public List<Product> getExpiringProducts(int days) {
        LocalDate threshold = LocalDate.now().plusDays(days);
        return productRepository.findByExpiryDateBefore(threshold);
    }
}

