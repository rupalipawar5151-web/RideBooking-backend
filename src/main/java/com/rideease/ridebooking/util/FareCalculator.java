package com.rideease.ridebooking.util;

import org.springframework.stereotype.Component;

@Component
public class FareCalculator {

    private static final double BASE_FARE = 50.0;
    private static final double PER_KM_RATE = 12.0;

    public double calculateFare(double distance) {
        return BASE_FARE + (distance * PER_KM_RATE);
    }
}
