package com.expensetracker.workerms.worker;

import java.util.List;

public interface WorkerService {

    List<Worker> getAllWorkers();

    Worker getWorkerById(Long id);

    Worker saveWorker(Worker worker);

    Worker updateWorker(Long id, Worker workerDetails);

    void deleteWorker(Long id);
}
