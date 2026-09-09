package com.mateo.smart_store_assistant.repository;

import com.mateo.smart_store_assistant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for User entity database operations.
 * Extends JpaRepository to provide standard CRUD operations.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    /**
     * Finds a user by their username.
     * Used during authentication to load user credentials.
     *
     * @param username the username to search for
     * @return an Optional containing the user if found, empty otherwise
     */
    Optional<User> findByUsername(String username);
}















