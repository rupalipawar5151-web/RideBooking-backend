package com.rideease.ridebooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class RideBookRequest {

    @NotNull(message = "User id is required")
    private Long userId;

    @NotBlank(message = "Pickup location is required")
    private String pickupLocation;

    @NotBlank(message = "Drop location is required")
    private String dropLocation;

    @Positive(message = "Distance must be greater than 0")
    private Double distance;

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}