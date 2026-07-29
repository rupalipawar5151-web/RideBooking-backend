package com.rideease.ridebooking.controller;

import com.rideease.ridebooking.dto.ApiResponse;
import com.rideease.ridebooking.dto.RideBookRequest;
import com.rideease.ridebooking.entity.Ride;
import com.rideease.ridebooking.service.RideService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    // POST /api/rides/book
    @PostMapping("/book")
    public ResponseEntity<ApiResponse<Ride>> bookRide(@Valid @RequestBody RideBookRequest request) {
        Ride ride = rideService.bookRide(request);
        ApiResponse<Ride> response = new ApiResponse<>(true, "Ride booked successfully", ride);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // PUT /api/rides/{id}/start
    @PutMapping("/{id}/start")
    public ResponseEntity<ApiResponse<Ride>> startRide(@PathVariable Long id) {
        Ride ride = rideService.startRide(id);
        ApiResponse<Ride> response = new ApiResponse<>(true, "Ride started successfully", ride);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // PUT /api/rides/{id}/complete
    @PutMapping("/{id}/complete")
    public ResponseEntity<ApiResponse<Ride>> completeRide(@PathVariable Long id) {
        Ride ride = rideService.completeRide(id);
        ApiResponse<Ride> response = new ApiResponse<>(true, "Ride completed successfully", ride);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // PUT /api/rides/{id}/cancel
    @PutMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<Ride>> cancelRide(@PathVariable Long id) {
        Ride ride = rideService.cancelRide(id);
        ApiResponse<Ride> response = new ApiResponse<>(true, "Ride cancelled successfully", ride);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // GET /api/rides
    @GetMapping
    public ResponseEntity<ApiResponse<List<Ride>>> getAllRides() {
        List<Ride> rides = rideService.getAllRides();
        ApiResponse<List<Ride>> response = new ApiResponse<>(true, "Rides fetched successfully", rides);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // GET /api/rides/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Ride>>> getRideHistoryByUser(@PathVariable Long userId) {
        List<Ride> rides = rideService.getRideHistoryByUser(userId);
        ApiResponse<List<Ride>> response = new ApiResponse<>(true, "Ride history fetched successfully", rides);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}