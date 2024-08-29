package com.expensetracker.workms.work;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workId;

    @Column(nullable = false)
    private String name;

    @Column(length = 1024)
    private String description;

    @Column(nullable = false)
    private BigDecimal amount;

    private Integer days;

    private Integer totalWorkers;

    private BigDecimal AmountPaid;

    @Column(nullable = false)
    private BigDecimal salary;

    @Column(nullable = false)
    private Long leadWorkerId;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    // Getters and setters

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
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
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
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
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
        if (totalWorkers != null && totalWorkers < 0) {
            throw new IllegalArgumentException("Total workers cannot be negative");
        }
        this.totalWorkers = totalWorkers;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        if (salary == null || salary.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Salary cannot be null or negative");
        }
        this.salary = salary;
    }

    public Long getLeadWorkerId() {
        return leadWorkerId;
    }

    public void setLeadWorkerId(Long leadWorkerId) {
        if (leadWorkerId == null || leadWorkerId <= 0) {
            throw new IllegalArgumentException("Lead Worker ID must be positive");
        }
        this.leadWorkerId = leadWorkerId;
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
        return AmountPaid;
    }

    public void setAmountPaid(BigDecimal AmountPaid) {
        if (AmountPaid == null) this.AmountPaid = BigDecimal.valueOf(0);

        else {
            if (AmountPaid.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("AmountPaid cannot be null or negative");
            }

            this.AmountPaid = AmountPaid;

        }
    }
}
