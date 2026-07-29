# RideEase Backend

A simple, beginner-friendly **Ride Booking Backend** (Uber-style) built with **Java Spring Boot**.

## Features
- User registration & login
- Driver management (add, view, update status, delete)
- Ride booking with **automatic driver assignment**
- Automatic **fare calculation** (₹50 base + ₹12/km)
- Ride lifecycle management: BOOKED → DRIVER_ASSIGNED → STARTED → COMPLETED / CANCELLED
- Global exception handling with clean JSON error responses
- Input validation on all endpoints

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL
- Maven

## Architecture
Follows standard MVC layered architecture:
