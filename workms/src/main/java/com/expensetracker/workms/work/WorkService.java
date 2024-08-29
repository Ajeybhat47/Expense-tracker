package com.expensetracker.workms.work;

import com.expensetracker.workms.work.dto.WorkDTO;
import com.expensetracker.workms.work.Work;
import com.expensetracker.workms.exception.ResourceNotFoundException;
import jakarta.ws.rs.BadRequestException;

import java.math.BigDecimal;
import java.util.List;

public interface WorkService {

    List<WorkDTO> getAllWorks();

    WorkDTO getWorkById(Long id) throws ResourceNotFoundException;

    Work saveWork(Work work);

    WorkDTO updateWork(Long id, WorkDTO workDTO) throws ResourceNotFoundException, BadRequestException;

    void deleteWork(Long id) throws ResourceNotFoundException;

    List<WorkDTO> getWorksByWorkerId(Long workerId);

    WorkDTO updateWorkAmount(Long id, BigDecimal amount);
}


