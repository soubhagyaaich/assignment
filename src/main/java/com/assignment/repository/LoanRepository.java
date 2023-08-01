package com.assignment.repository;

import com.assignment.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {

    List<Loan> findByDueDateBeforeAndRemainingAmountGreaterThan(Date dueDate, BigDecimal remainingAmount);

    @Query("SELECT l.lender.lenderId, SUM(l.remainingAmount) FROM Loan l GROUP BY l.lender.lenderId")
    List<Object[]> aggregateByLender();

    @Query("SELECT l.interestPerDay, SUM(l.remainingAmount) FROM Loan l GROUP BY l.interestPerDay")
    List<Object[]> aggregateByInterest();

    @Query("SELECT l.customer.customerId, SUM(l.remainingAmount) FROM Loan l GROUP BY l.customer.customerId")
    List<Object[]> aggregateByCustomerId();
}
