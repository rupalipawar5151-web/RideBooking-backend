package com.rideease.ridebooking.service;

import com.rideease.ridebooking.dto.DriverRequest;
import com.rideease.ridebooking.entity.Driver;
import com.rideease.ridebooking.entity.DriverStatus;
import com.rideease.ridebooking.exception.DriverNotFoundException;
import com.rideease.ridebooking.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    // ===== Add a new driver =====
    public Driver addDriver(DriverRequest request) {
        Driver driver = new Driver(
                request.getDriverName(),
                request.getPhone(),
                request.getVehicleNumber(),
                request.getVehicleType()
        );
        return driverRepository.save(driver);
    }

    // ===== View all drivers =====
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    // ===== Update driver status (AVAILABLE / BUSY) =====
    public Driver updateDriverStatus(Long driverId, DriverStatus status) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new DriverNotFoundException("Driver not found with id: " + driverId));

        driver.setStatus(status);
        return driverRepository.save(driver);
    }

    // ===== Delete a driver =====
    public void deleteDriver(Long driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new DriverNotFoundException("Driver not found with id: " + driverId));

        driverRepository.delete(driver);
    }
}