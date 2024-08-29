package com.expensetracker.workms.work;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WorkRepository extends JpaRepository<Work, Long> {
    List<Work> findByLeadWorkerId(Long workerId);
}