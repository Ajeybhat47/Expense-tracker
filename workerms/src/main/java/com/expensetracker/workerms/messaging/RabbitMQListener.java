package com.expensetracker.workerms.messaging;

import com.expensetracker.workerms.worker.Worker;
import com.expensetracker.workerms.worker.WorkerService;
import com.expensetracker.workerms.messaging.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RabbitMQListener {

    @Autowired
    private WorkerService workerService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void listen(String message) {
        // Assuming the message format is "UpdatePaid:workerId:amount"
        if (message.startsWith("UpdatePaid:")) {
            String[] parts = message.split(":");
            Long workerId = Long.parseLong(parts[1]);
            BigDecimal amount = new BigDecimal(parts[2]);
            updateWorkerPaid(workerId, amount);
        }
    }

    private void updateWorkerPaid(Long workerId, BigDecimal amount) {
        Worker worker = workerService.getWorkerById(workerId);
        worker.setPaid(worker.getPaid().add(amount));
        workerService.saveWorker(worker);
    }
}
