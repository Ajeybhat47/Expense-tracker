package com.expensetracker.dayTrackingms.daytracking;

import com.expensetracker.dayTrackingms.daytracking.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayTrackingService {

    @Autowired
    private DayTrackingRepository dayTrackingRepository;

    public List<DayTracking> getAllDayTrackings() {
        return dayTrackingRepository.findAll();
    }

    public DayTracking getDayTrackingById(Long id) {
        return dayTrackingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DayTracking not found with id " + id));
    }

    public DayTracking saveDayTracking(DayTracking dayTracking) {
        return dayTrackingRepository.save(dayTracking);
    }

    public void deleteDayTracking(Long id) {
        if (!dayTrackingRepository.existsById(id)) {
            throw new ResourceNotFoundException("DayTracking not found with id " + id);
        }
        dayTrackingRepository.deleteById(id);
    }
}
