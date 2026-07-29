package com.rideease.ridebooking.repository;

import com.rideease.ridebooking.entity.Driver;
import com.rideease.ridebooking.entity.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    // पहिला AVAILABLE driver शोधण्यासाठी (auto-assign साठी उपयोगी)
    Optional<Driver> findFirstByStatus(DriverStatus status);

    // सगळे drivers एका status चे (उदा. सगळे AVAILABLE)
    List<Driver> findByStatus(DriverStatus status);
}