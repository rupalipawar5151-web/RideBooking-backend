package com.rideease.ridebooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full name is required")
    @Size(min = 3, max = 50, message = "Name should be 3 to 50 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    private String phone;

    @NotBlank(message = "Password is required")
    private String password;

    private LocalDateTime createdAt;

    // ===== Constructors =====
    public User() {
    }

    public User(String fullName, String email, String phone, String password) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }

    // ===== Getters and Setters =====
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
