package com.mateo.smart_store_assistant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a system user with authentication credentials.
 * Stores hashed passwords — plain text passwords are never persisted.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * Auto-generated unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Unique username used for authentication.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * BCrypt-hashed password. Never stored in plain text.
     */
    @Column(nullable = false)
    private String password;

    /**
     * User role for authorization (e.g., "ROLE_ADMIN", "ROLE_USER").
     */
    @Column(nullable = false)
    private String role;
}

















