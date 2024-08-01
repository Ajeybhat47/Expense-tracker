package com.expensetracker.workerms.worker;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @GetMapping
    public ResponseEntity<List<Worker>> getAllWorkers() {
        List<Worker> workers = workerService.getAllWorkers();
        return ResponseEntity.ok(workers);
    }

    @GetMapping(params = "id")
    public ResponseEntity<?> getWorkerById(@RequestParam Long id) {
        try {
            Worker worker = workerService.getWorkerById(id);
            return ResponseEntity.ok(worker);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createWorker(@RequestBody Worker worker) {
        try {
            Worker createdWorker = workerService.saveWorker(worker);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWorker);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateWorker(@RequestParam Long id, @RequestBody Worker workerDetails) {
        try {
            Worker updatedWorker = workerService.updateWorker(id, workerDetails);
            return ResponseEntity.ok(updatedWorker);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteWorker(@RequestParam Long id) {
        try {
            workerService.deleteWorker(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
