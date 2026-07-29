package com.rideease.ridebooking.service;

import com.rideease.ridebooking.dto.UserLoginRequest;
import com.rideease.ridebooking.dto.UserRegisterRequest;
import com.rideease.ridebooking.entity.User;
import com.rideease.ridebooking.exception.UserNotFoundException;
import com.rideease.ridebooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // ===== Register a new user =====
    public User registerUser(UserRegisterRequest request) {

        // Check karto ki email aadhich vaparat aahe ka
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered: " + request.getEmail());
        }

        User user = new User(
                request.getFullName(),
                request.getEmail(),
                request.getPhone(),
                request.getPassword()
        );

        return userRepository.save(user);
    }

    // ===== Login user (simple check, no JWT/security) =====
    public User loginUser(UserLoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(
                        "No user found with email: " + request.getEmail()));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("Incorrect password");
        }

        return user;
    }

    // ===== Get user profile by id =====
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }
}
