package com.expensetracker.dayTrackingms.daytracking;

import com.expensetracker.dayTrackingms.daytracking.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/daytrackings")
public class DayTrackingController {

    @Autowired
    private DayTrackingService dayTrackingService;

    @GetMapping
    public ResponseEntity<?> getAllDayTrackings() {
        try {
            List<DayTracking> dayTrackings = dayTrackingService.getAllDayTrackings();
            return ResponseEntity.ok(dayTrackings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching day trackings");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDayTrackingById(@PathVariable Long id) {
        try {
            DayTracking dayTracking = dayTrackingService.getDayTrackingById(id);
            return ResponseEntity.ok(dayTracking);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createDayTracking(@RequestBody DayTracking dayTracking) {
        try {
            DayTracking createdDayTracking = dayTrackingService.saveDayTracking(dayTracking);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDayTracking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred while creating day tracking");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDayTracking(@PathVariable Long id) {
        try {
            dayTrackingService.deleteDayTracking(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
