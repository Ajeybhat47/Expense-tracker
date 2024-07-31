package com.expensetracker.workerms.worker;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workerId;

    @Column(nullable = false)
    private String name;

    private String address;

    private Integer totalWorks;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @Column
    private BigDecimal paid;

    // Getters and Setters

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTotalWorks() {
        return totalWorks;
    }

    public void setTotalWorks(Integer totalWorks) {
        this.totalWorks = totalWorks;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPaid() {
        return paid;
    }

    public void setPaid(BigDecimal paid) {
        this.paid = paid;
    }
}

