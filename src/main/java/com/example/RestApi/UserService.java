package com.example.RestApi;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final Map<UUID, User> users = new HashMap<>();  // In-memory data structure

    public void createUser(User user) {
        if (!isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email format!");
        }
        user.setId(UUID.randomUUID());  // Generate a new UUID for the user
        users.put(user.getId(), user);  // Store the user in the HashMap
    }

    public User getUser(UUID id) {
        return users.get(id);  // Retrieve a user by UUID
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());  // Return a list of all users
    }

    public void updateUser(UUID id, User user) {
        if (!users.containsKey(id)) {
            throw new IllegalArgumentException("User not found!");
        }
        if (!isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email format!");
        }
        user.setId(id);  // Ensure the user ID remains unchanged
        users.put(id, user);  // Update the user in the HashMap
    }

    public boolean deleteUser(UUID id) {
        return users.remove(id) != null;  // Remove the user from the HashMap
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);  // Basic email validation
    }
}