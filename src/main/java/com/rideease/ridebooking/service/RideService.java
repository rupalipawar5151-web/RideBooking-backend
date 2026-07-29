
package com.rideease.ridebooking.service;

import com.rideease.ridebooking.dto.RideBookRequest;
import com.rideease.ridebooking.entity.*;
import com.rideease.ridebooking.exception.*;
import com.rideease.ridebooking.repository.DriverRepository;
import com.rideease.ridebooking.repository.RideRepository;
import com.rideease.ridebooking.repository.UserRepository;
import com.rideease.ridebooking.util.FareCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private FareCalculator fareCalculator;

    // ===== Book a new ride =====
    public Ride bookRide(RideBookRequest request) {

        // check user exits or not
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(
                        "User not found with id: " + request.getUserId()));

        // new ride
        Ride ride = new Ride(
                user,
                request.getPickupLocation(),
                request.getDropLocation(),
                request.getDistance()
        );

        // Fare calculate 
        double fare = fareCalculator.calculateFare(request.getDistance());
        ride.setFare(fare);

        // firstly AVAILABLE driver auto-assign 
        Driver availableDriver = driverRepository.findFirstByStatus(DriverStatus.AVAILABLE)
                .orElseThrow(() -> new DriverNotAvailableException(
                        "No drivers available right now. Please try again later."));

        ride.setDriver(availableDriver);
        ride.setRideStatus(RideStatus.DRIVER_ASSIGNED);

        // driver are busy he on another drive
        availableDriver.setStatus(DriverStatus.BUSY);
        driverRepository.save(availableDriver);

        // Ride save and return
        return rideRepository.save(ride);
    }

    // ===== Start a ride =====
    public Ride startRide(Long rideId) {
        Ride ride = getRideOrThrow(rideId);

        if (ride.getRideStatus() != RideStatus.DRIVER_ASSIGNED) {
            throw new InvalidRideStatusException(
                    "Ride cannot be started. Current status: " + ride.getRideStatus());
        }

        ride.setRideStatus(RideStatus.STARTED);
        return rideRepository.save(ride);
    }

    // ===== Complete a ride =====
    public Ride completeRide(Long rideId) {
        Ride ride = getRideOrThrow(rideId);

        if (ride.getRideStatus() != RideStatus.STARTED) {
            throw new InvalidRideStatusException(
                    "Ride cannot be completed. Current status: " + ride.getRideStatus());
        }

        ride.setRideStatus(RideStatus.COMPLETED);

        // Driver are available because ride is over
        Driver driver = ride.getDriver();
        driver.setStatus(DriverStatus.AVAILABLE);
        driverRepository.save(driver);

        return rideRepository.save(ride);
    }

    // ===== Cancel a ride =====
    public Ride cancelRide(Long rideId) {
        Ride ride = getRideOrThrow(rideId);

        if (ride.getRideStatus() == RideStatus.COMPLETED
                || ride.getRideStatus() == RideStatus.CANCELLED) {
            throw new InvalidRideStatusException(
                    "Ride cannot be cancelled. Current status: " + ride.getRideStatus());
        }

        ride.setRideStatus(RideStatus.CANCELLED);

        //  driver are assign so he is AVAILABLE for next ride
        if (ride.getDriver() != null) {
            Driver driver = ride.getDriver();
            driver.setStatus(DriverStatus.AVAILABLE);
            driverRepository.save(driver);
        }

        return rideRepository.save(ride);
    }

    // ===== View all rides =====
    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    // ===== View ride history of a specific user =====
    public List<Ride> getRideHistoryByUser(Long userId) {
        return rideRepository.findByUserId(userId);
    }

    // ===== Helper method (reused in start/complete/cancel) =====
    private Ride getRideOrThrow(Long rideId) {
        return rideRepository.findById(rideId)
                .orElseThrow(() -> new RideNotFoundException("Ride not found with id: " + rideId));
    }
}