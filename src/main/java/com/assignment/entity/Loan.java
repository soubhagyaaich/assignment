package com.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Loan {

    @Id
    private String loanId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "lender_id")
    private Lender lender;

    private BigDecimal amount;
    private BigDecimal remainingAmount;
    private Date paymentDate;
    private BigDecimal interestPerDay;
    private Date dueDate;
    private BigDecimal penaltyPerDay;
    private boolean canceled;
}
