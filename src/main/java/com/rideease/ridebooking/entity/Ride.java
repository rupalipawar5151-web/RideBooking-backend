package com.rideease.ridebooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Table(name = "rides")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ===== Relationship: Many Rides belong to One User =====
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // ===== Relationship: Many Rides belong to One Driver =====
    // सुरुवातीला ride book झाल्यावर driver नसतोच (नंतर assign होतो),
    // म्हणून हा null असू शकतो
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @NotBlank(message = "Pickup location is required")
    private String pickupLocation;

    @NotBlank(message = "Drop location is required")
    private String dropLocation;

    @Positive(message = "Distance must be greater than 0")
    private Double distance;

    private Double fare;

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    private LocalDateTime bookingTime;

    // ===== Constructors =====
    public Ride() {
    }

    public Ride(User user, String pickupLocation, String dropLocation, Double distance) {
        this.user = user;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.distance = distance;
        this.rideStatus = RideStatus.BOOKED;   // नवीन ride सुरुवातीला BOOKED status मध्ये असतो
        this.bookingTime = LocalDateTime.now();
    }

    // ===== Getters and Setters =====
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }
}