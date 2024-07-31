package com.expensetracker.workms.work.client;


import com.expensetracker.workms.work.dto.WorkerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "WORKER-MS", url = "http://localhost:8080")
public interface WorkerClientService {

        @GetMapping("/workers/{id}")
        WorkerDTO getWorkerById(@PathVariable Long id);

}
