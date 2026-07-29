package com.rideease.ridebooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Driver name is required")
    private String driverName;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    private String phone;

    @NotBlank(message = "Vehicle number is required")
    private String vehicleNumber;

    @NotBlank(message = "Vehicle type is required")
    private String vehicleType;

    @Enumerated(EnumType.STRING)
    private DriverStatus status;

    private Double rating;

    // ===== Constructors =====
    public Driver() {
    }

    public Driver(String driverName, String phone, String vehicleNumber, String vehicleType) {
        this.driverName = driverName;
        this.phone = phone;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.status = DriverStatus.AVAILABLE;   // नवीन driver default AVAILABLE असतो
        this.rating = 5.0;           // सुरुवातीला default rating
    }

    // ===== Getters and Setters =====
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

   public DriverStatus getStatus() {
    return status;
}

public void setStatus(DriverStatus status) {
    this.status = status;
}

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
