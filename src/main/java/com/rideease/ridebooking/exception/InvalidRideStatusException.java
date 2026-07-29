package com.rideease.ridebooking.exception;

public class InvalidRideStatusException extends RuntimeException {

    public InvalidRideStatusException(String message) {
        super(message);
    }
}