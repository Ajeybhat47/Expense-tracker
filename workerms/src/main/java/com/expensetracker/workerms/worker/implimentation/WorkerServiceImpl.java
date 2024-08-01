package com.expensetracker.workerms.worker.implimentation;

import com.expensetracker.workerms.worker.ResourceNotFoundException;
import com.expensetracker.workerms.worker.Worker;
import com.expensetracker.workerms.worker.WorkerRepository;
import com.expensetracker.workerms.worker.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    @Override
    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    @Override
    public Worker getWorkerById(Long id) {
        return workerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Worker not found with id " + id));
    }

    @Override
    public Worker saveWorker(Worker worker) {
        validateWorker(worker);
        return workerRepository.save(worker);
    }

    @Override
    public Worker updateWorker(Long id, Worker workerDetails) {
        Worker existingWorker = getWorkerById(id);

        if (Objects.nonNull(workerDetails.getName())) {
            existingWorker.setName(workerDetails.getName());
        }

        if (Objects.nonNull(workerDetails.getAddress())) {
            existingWorker.setAddress(workerDetails.getAddress());
        }

        if (Objects.nonNull(workerDetails.getTotalWorks())) {
            existingWorker.setTotalWorks(workerDetails.getTotalWorks());
        }

        if (Objects.nonNull(workerDetails.getTotalAmount())) {
            existingWorker.setTotalAmount(workerDetails.getTotalAmount());
        }

        if (Objects.nonNull(workerDetails.getPaid())) {
            existingWorker.setPaid(workerDetails.getPaid());
        }

        validateWorker(existingWorker);
        return workerRepository.save(existingWorker);
    }

    @Override
    public void deleteWorker(Long id) {
        if (!workerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Worker not found with id " + id);
        }
        workerRepository.deleteById(id);
    }

    private void validateWorker(Worker worker) {
        if (worker.getName() == null || worker.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (worker.getTotalAmount() == null) {
            throw new IllegalArgumentException("Total amount cannot be null");
        }
        if (worker.getPaid() != null && worker.getPaid().compareTo(worker.getTotalAmount()) > 0) {
            throw new IllegalArgumentException("Paid amount cannot exceed total amount");
        }
    }
}
