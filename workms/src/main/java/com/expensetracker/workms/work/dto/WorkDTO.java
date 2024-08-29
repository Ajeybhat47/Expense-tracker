package com.expensetracker.workms.work.dto;

import java.math.BigDecimal;
import java.util.Date;

public class WorkDTO {
    private Long workId;
    private String name;
    private String description;
    private BigDecimal amount;
    private Integer days;
    private Integer totalWorkers;
    private BigDecimal salary;
    private BigDecimal amountPaid;
    private WorkerDTO leadWorker; // Use WorkerDTO instead of Long
    private Date startDate;
    private Date endDate;

    // Getters and Setters
    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getTotalWorkers() {
        return totalWorkers;
    }

    public void setTotalWorkers(Integer totalWorkers) {
        this.totalWorkers = totalWorkers;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public WorkerDTO getLeadWorker() {
        return leadWorker;
    }

    public void setLeadWorker(WorkerDTO leadWorker) {
        this.leadWorker = leadWorker;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }
}
