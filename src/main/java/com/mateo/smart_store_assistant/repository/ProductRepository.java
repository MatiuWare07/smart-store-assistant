package com.mateo.smart_store_assistant.repository;

import com.mateo.smart_store_assistant.model.Product;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for Product entity.
 * Extends JpaRepository to provide standard CRUD operations.
 * Spring Data JPA automatically generates the implementation at runtime.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Finds all products whose expiry date is before or equal to the given date.
     * Used to generate expiry alerts for products about to expire.
     *
     * @param date the reference date to check against
     * @return list of products expiring on or before the given date
     */
    List<Product> findByExpiryDateBefore(LocalDate date);
    /**
     * Finds all products belonging to a specific category.
     *
     * @param category the category name to filter by
     * @return list of products in the given category
     */
    List<Product> findByCategory(String category);
}
