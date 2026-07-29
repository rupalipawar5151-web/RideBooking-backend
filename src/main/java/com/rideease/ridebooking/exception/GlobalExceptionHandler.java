package com.rideease.ridebooking.exception;

import com.rideease.ridebooking.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ===== Handle: User not found =====
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleUserNotFound(UserNotFoundException ex) {
        ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // ===== Handle: Driver not found =====
    @ExceptionHandler(DriverNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleDriverNotFound(DriverNotFoundException ex) {
        ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // ===== Handle: Driver not available (auto-assign failed) =====
    @ExceptionHandler(DriverNotAvailableException.class)
    public ResponseEntity<ApiResponse<Object>> handleDriverNotAvailable(DriverNotAvailableException ex) {
        ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    // ===== Handle: Ride not found =====
    @ExceptionHandler(RideNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleRideNotFound(RideNotFoundException ex) {
        ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // ===== Handle: Invalid ride status transition =====
    @ExceptionHandler(InvalidRideStatusException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidRideStatus(InvalidRideStatusException ex) {
        ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // ===== Handle: Generic bad request (e.g. email already registered, wrong password) =====
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgument(IllegalArgumentException ex) {
        ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // ===== Handle: @Valid validation errors (e.g. @NotBlank, @Email failed) =====
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        ApiResponse<Object> response = new ApiResponse<>(false, "Validation failed", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // ===== Handle: Any other unexpected exception (safety net) =====
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex) {
        ApiResponse<Object> response = new ApiResponse<>(false, "Something went wrong: " + ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}