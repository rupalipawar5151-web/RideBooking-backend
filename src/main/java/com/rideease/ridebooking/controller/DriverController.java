package com.rideease.ridebooking.controller;

import com.rideease.ridebooking.dto.ApiResponse;
import com.rideease.ridebooking.dto.DriverRequest;
import com.rideease.ridebooking.entity.Driver;
import com.rideease.ridebooking.entity.DriverStatus;
import com.rideease.ridebooking.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    // POST /api/drivers
    @PostMapping
    public ResponseEntity<ApiResponse<Driver>> addDriver(@Valid @RequestBody DriverRequest request) {
        Driver driver = driverService.addDriver(request);
        ApiResponse<Driver> response = new ApiResponse<>(true, "Driver added successfully", driver);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // GET /api/drivers
    @GetMapping
    public ResponseEntity<ApiResponse<List<Driver>>> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        ApiResponse<List<Driver>> response = new ApiResponse<>(true, "Drivers fetched successfully", drivers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // PUT /api/drivers/{id}/status
    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Driver>> updateDriverStatus(
            @PathVariable Long id,
            @RequestParam DriverStatus status) {
        Driver driver = driverService.updateDriverStatus(id, status);
        ApiResponse<Driver> response = new ApiResponse<>(true, "Driver status updated successfully", driver);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // DELETE /api/drivers/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        ApiResponse<String> response = new ApiResponse<>(true, "Driver deleted successfully", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}