package com.mateo.smart_store_assistant.service;

import com.mateo.smart_store_assistant.model.User;
import com.mateo.smart_store_assistant.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for user management and authentication.
 * Implements UserDetailsService to integrate with Spring Security.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs the UserService with required dependencies.
     *
     * @param userRepository  repository for user database operations
     * @param passwordEncoder BCrypt encoder for hashing passwords
     */
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Loads a user by username for Spring Security authentication.
     * Called automatically by the authentication filter on each request.
     *
     * @param username the username to look up
     * @return UserDetails object containing credentials and authorities
     * @throws UsernameNotFoundException if no user exists with the given username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with username" + username
                ));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole()))
        );
    }

    /**
     * Registers a new user with a BCrypt-hashed password.
     *
     * @param username the desired username
     * @param password the plain text password to be hashed
     * @param role     the role to assign (e.g., "ROLE_ADMIN", "ROLE_USER")
     * @return the saved User entity
     * @throws IllegalArgumentException if the username is already taken
     */
    public User registerUser(String username, String password, String role){
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists: " + username);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        return userRepository.save(user);
    }
}
















