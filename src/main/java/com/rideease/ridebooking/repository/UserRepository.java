package com.rideease.ridebooking.repository;

import com.rideease.ridebooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Email वरून User शोधण्यासाठी (login साठी उपयोगी)
    Optional<User> findByEmail(String email);
}
