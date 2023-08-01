package com.assignment.service;

import com.assignment.entity.Loan;
import com.assignment.repository.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Transactional
    public Loan createLoan(Loan loan) {
        if (loan.getPaymentDate().after(loan.getDueDate())) {
            throw new RuntimeException("Payment date cannot be greater than the due date.");
        }

        return loanRepository.save(loan);
    }

    @Transactional(readOnly = true)
    public List<Loan> findOverdueLoans() {
        return loanRepository.findByDueDateBeforeAndRemainingAmountGreaterThan(new Date(), BigDecimal.ZERO);
    }

    @Transactional(readOnly = true)
    public List<Object[]> aggregateByLender() {
        return loanRepository.aggregateByLender();
    }

    @Transactional(readOnly = true)
    public List<Object[]> aggregateByInterest() {
        return loanRepository.aggregateByInterest();
    }

    @Transactional(readOnly = true)
    public List<Object[]> aggregateByCustomerId() {
        return loanRepository.aggregateByCustomerId();
    }
}

