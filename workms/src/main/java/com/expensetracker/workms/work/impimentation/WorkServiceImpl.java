package com.expensetracker.workms.work.impimentation;

import com.expensetracker.workms.work.Work;
import com.expensetracker.workms.work.WorkRepository;
import com.expensetracker.workms.work.WorkService;
import com.expensetracker.workms.work.client.WorkerClientService;
import com.expensetracker.workms.work.dto.WorkDTO;
import com.expensetracker.workms.exception.ResourceNotFoundException;
import com.expensetracker.workms.work.dto.WorkerDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private WorkerClientService workerClientService;

    @Retry(name = "workerbreaker")
    public List<WorkDTO> getAllWorks() {
        List<Work> works = workRepository.findAll();
        return works.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public WorkDTO getWorkById(Long id) {
        Work work = workRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work not found with id " + id));
        return convertToDTO(work);
    }

    @Override
    public Work saveWork(Work work) {
        validateWork(work);
        return workRepository.save(work);
    }

    public WorkDTO updateWork(Long id, WorkDTO workDTO) {
        Work existingWork = workRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work not found with id " + id));
        applyUpdates(existingWork, workDTO);
        validateWorkDTO(convertToDTO(existingWork));
        Work updatedWork = workRepository.save(existingWork);
        return convertToDTO(updatedWork);
    }

    public void deleteWork(Long id) {
        if (!workRepository.existsById(id)) {
            throw new ResourceNotFoundException("Work not found with id " + id);
        }
        workRepository.deleteById(id);
    }

    @Override
    public List<WorkDTO> getWorksByWorkerId(Long workerId) {
        return List.of();
    }

    private void validateWork(Work work) {
        if (work.getName() == null || work.getName().trim().isEmpty()) {
            throw new BadRequestException("Name cannot be null or empty");
        }

        if (work.getAmount() == null || work.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Amount must be greater than zero");
        }

        if (work.getSalary() == null || work.getSalary().compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequestException("Salary cannot be null or negative");
        }

        if (work.getLeadWorkerId() == null) {
            throw new BadRequestException("Lead Worker information is missing or incomplete");
        }

        // Verify lead worker exists
        WorkerDTO workerDTO = workerClientService.getWorkerById(work.getLeadWorkerId());
        if (workerDTO == null) {
            throw new BadRequestException("Lead Worker ID does not exist");
        }

        if (work.getStartDate() != null && work.getEndDate() != null) {
            if (work.getStartDate().after(work.getEndDate())) {
                throw new BadRequestException("Start date must be before end date");
            }
        }
    }

    private void validateWorkDTO(WorkDTO workDTO) {
        if (workDTO.getName() == null || workDTO.getName().trim().isEmpty()) {
            throw new BadRequestException("Name cannot be null or empty");
        }

        if (workDTO.getAmount() == null || workDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Amount must be greater than zero");
        }

        if (workDTO.getSalary() == null || workDTO.getSalary().compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequestException("Salary cannot be null or negative");
        }

        if (workDTO.getLeadWorker() == null || workDTO.getLeadWorker().getWorkerId() == null) {
            throw new BadRequestException("Lead Worker information is missing or incomplete");
        }

        // Verify lead worker exists
        WorkerDTO workerDTO = workerClientService.getWorkerById(workDTO.getLeadWorker().getWorkerId());
        if (workerDTO == null) {
            throw new BadRequestException("Lead Worker ID does not exist");
        }

        if (workDTO.getStartDate() != null && workDTO.getEndDate() != null) {
            if (workDTO.getStartDate().after(workDTO.getEndDate())) {
                throw new BadRequestException("Start date must be before end date");
            }
        }
    }

    private WorkDTO convertToDTO(Work work) {
        WorkDTO dto = new WorkDTO();
        dto.setWorkId(work.getWorkId());
        dto.setName(work.getName());
        dto.setDescription(work.getDescription());
        dto.setAmount(work.getAmount());
        dto.setDays(work.getDays());
        dto.setTotalWorkers(work.getTotalWorkers());
        dto.setSalary(work.getSalary());

        WorkerDTO leadWorkerDTO = workerClientService.getWorkerById(work.getLeadWorkerId());
        dto.setLeadWorker(leadWorkerDTO);

        dto.setStartDate(work.getStartDate());
        dto.setEndDate(work.getEndDate());
        return dto;
    }

    private Work convertToEntity(WorkDTO dto) {
        Work work = new Work();
        work.setWorkId(dto.getWorkId());
        work.setName(dto.getName());
        work.setDescription(dto.getDescription());
        work.setAmount(dto.getAmount());
        work.setDays(dto.getDays());
        work.setTotalWorkers(dto.getTotalWorkers());
        work.setSalary(dto.getSalary());

        if (dto.getLeadWorker() != null && dto.getLeadWorker().getWorkerId() != null) {
            work.setLeadWorkerId(dto.getLeadWorker().getWorkerId());
        }

        work.setStartDate(dto.getStartDate());
        work.setEndDate(dto.getEndDate());
        return work;
    }

    private void applyUpdates(Work existingWork, WorkDTO workDTO) {
        if (workDTO.getName() != null) {
            existingWork.setName(workDTO.getName());
        }

        if (workDTO.getDescription() != null) {
            existingWork.setDescription(workDTO.getDescription());
        }

        if (workDTO.getAmount() != null) {
            existingWork.setAmount(workDTO.getAmount());
        }

        if (workDTO.getDays() != null) {
            existingWork.setDays(workDTO.getDays());
        }

        if (workDTO.getTotalWorkers() != null) {
            existingWork.setTotalWorkers(workDTO.getTotalWorkers());
        }

        if (workDTO.getSalary() != null) {
            existingWork.setSalary(workDTO.getSalary());
        }

        if (workDTO.getLeadWorker() != null && workDTO.getLeadWorker().getWorkerId() != null) {
            existingWork.setLeadWorkerId(workDTO.getLeadWorker().getWorkerId());
        }

        if (workDTO.getStartDate() != null) {
            existingWork.setStartDate(workDTO.getStartDate());
        }

        if (workDTO.getEndDate() != null) {
            existingWork.setEndDate(workDTO.getEndDate());
        }
    }
}
