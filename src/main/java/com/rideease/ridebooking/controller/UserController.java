package com.rideease.ridebooking.controller;

import com.rideease.ridebooking.dto.ApiResponse;
import com.rideease.ridebooking.dto.UserLoginRequest;
import com.rideease.ridebooking.dto.UserRegisterRequest;
import com.rideease.ridebooking.entity.User;
import com.rideease.ridebooking.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // POST /api/users/register
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> registerUser(@Valid @RequestBody UserRegisterRequest request) {
        User user = userService.registerUser(request);
        ApiResponse<User> response = new ApiResponse<>(true, "User registered successfully", user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // POST /api/users/login
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<User>> loginUser(@Valid @RequestBody UserLoginRequest request) {
        User user = userService.loginUser(request);
        ApiResponse<User> response = new ApiResponse<>(true, "Login successful", user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // GET /api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserProfile(@PathVariable Long id) {
        User user = userService.getUserById(id);
        ApiResponse<User> response = new ApiResponse<>(true, "User fetched successfully", user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}