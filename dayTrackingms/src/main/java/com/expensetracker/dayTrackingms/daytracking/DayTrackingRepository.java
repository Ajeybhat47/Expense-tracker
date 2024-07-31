package com.expensetracker.dayTrackingms.daytracking;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DayTrackingRepository extends JpaRepository<DayTracking, Long> {
}
