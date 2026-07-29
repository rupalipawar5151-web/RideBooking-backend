package com.rideease.ridebooking.dto;

import jakarta.validation.constraints.NotBlank;

public class DriverRequest {

    @NotBlank(message = "Driver name is required")
    private String driverName;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @NotBlank(message = "Vehicle number is required")
    private String vehicleNumber;

    @NotBlank(message = "Vehicle type is required")
    private String vehicleType;

    // Getters and Setters
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}