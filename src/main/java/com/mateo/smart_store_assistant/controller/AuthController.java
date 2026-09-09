package com.mateo.smart_store_assistant.controller;

import com.mateo.smart_store_assistant.model.User;
import com.mateo.smart_store_assistant.security.JwtUtil;
import com.mateo.smart_store_assistant.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST controller for authentication operations.
 * Provides endpoints for user registration and login.
 * Public endpoints — no JWT token required to access them.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    /**
     * Constructs the AuthController with required dependencies.
     *
     * @param userService           service for user management operations
     * @param jwtUtil               utility for JWT token generation
     * @param authenticationManager Spring Security manager for credential validation
     */
    public AuthController(UserService userService, JwtUtil jwtUtil,
                          AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Registers a new user in the system.
     * Expects a JSON body with username, password, and role fields.
     *
     * @param request map containing "username", "password", and "role"
     * @return the created User entity with HTTP 201 status
     */
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody Map<String, String> request) {
        User user = userService.registerUser(
                request.get("username"),
                request.get("password"),
                request.get("role")
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    /**
     * Authenticates a user and returns a JWT token.
     * Expects a JSON body with username and password fields.
     *
     * @param request map containing "username" and "password"
     * @return JWT token string with HTTP 200 status, or 401 if credentials are invalid
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.get("username"),
                            request.get("password")
                    )
            );

            String token = jwtUtil.generateToken(request.get("username"));
            return ResponseEntity.ok(Map.of("token", token));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid username or password"));
        }
    }
}









