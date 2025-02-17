package com.example.RestApi;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final Map<UUID, User> users = new HashMap<>();

    // Create a user
    @PostMapping
    public User createUser(@RequestBody User user) {
        UUID id = UUID.randomUUID();
        user.setId(id);
        users.put(id, user);
        return user;
    }

    // Get all users
    @GetMapping
    public Collection<User> getAllUsers() {
        return users.values();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable UUID id) {
        return users.get(id);
    }

    // Update a user
    @PutMapping("/{id}")
public User updateUser(@PathVariable UUID id, @RequestBody User userDetails) {
    User user = users.get(id);
    if (user != null) {
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setAge(userDetails.getAge());
        return user;
    }
    return null;  // Return null or handle not found scenario
}

    // Delete a user
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable UUID id) {
        User user = users.remove(id);
        if (user != null) {
            return "User with ID " + id + " has been deleted.";
        }
        return "User not found.";
    }
}