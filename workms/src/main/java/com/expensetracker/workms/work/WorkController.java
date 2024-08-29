package com.expensetracker.workms.work;

import com.expensetracker.workms.work.dto.WorkDTO;
import com.expensetracker.workms.exception.ResourceNotFoundException;

import jakarta.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/works")
public class WorkController {

    @Autowired
    private WorkService workService;

    @GetMapping
    public ResponseEntity<?> getAllWorks() {
        try {
            List<WorkDTO> works = workService.getAllWorks();
            return ResponseEntity.ok(works);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching works");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorkById(@PathVariable Long id) {
        try {
            WorkDTO work = workService.getWorkById(id);
            return ResponseEntity.ok(work);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createWork(@RequestBody Work work) {
        try {
            Work createdWork = workService.saveWork(work);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWork);
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWork(@PathVariable Long id, @RequestBody WorkDTO workDTO) {
        try {
            WorkDTO updatedWork = workService.updateWork(id, workDTO);
            return ResponseEntity.ok(updatedWork);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/amount")
    public ResponseEntity<?> updateWorkAmountPaid(@PathVariable Long id, @RequestParam BigDecimal amount) {
        try {
            WorkDTO updatedWork = workService.updateWorkAmount(id, amount);
            return ResponseEntity.ok(updatedWork);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/worker/{workerId}")
    public ResponseEntity<?> getWorksByWorkerId(@PathVariable Long workerId) {
        try {
            List<WorkDTO> works = workService.getWorksByWorkerId(workerId);
            return ResponseEntity.ok(works);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWork(@PathVariable Long id) {
        try {
            workService.deleteWork(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
