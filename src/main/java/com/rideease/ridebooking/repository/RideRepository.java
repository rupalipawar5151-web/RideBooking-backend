package com.rideease.ridebooking.repository;

import com.rideease.ridebooking.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {

    // एका User चा संपूर्ण ride history पाहण्यासाठी
    List<Ride> findByUserId(Long userId);

    // एका Driver ने केलेल्या सगळ्या rides साठी (भविष्यात उपयोगी पडेल)
    List<Ride> findByDriverId(Long driverId);
}
