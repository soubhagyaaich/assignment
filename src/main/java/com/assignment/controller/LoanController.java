package com.assignment.controller;

import com.assignment.entity.Loan;
import com.assignment.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
        try {
            Loan createdLoan = loanService.createLoan(loan);
            return new ResponseEntity<>(createdLoan, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<Loan>> getOverdueLoans() {
        List<Loan> overdueLoans = loanService.findOverdueLoans();
        return new ResponseEntity<>(overdueLoans, HttpStatus.OK);
    }

    @GetMapping("/aggregate/lender")
    public ResponseEntity<List<Object[]>> aggregateByLender() {
        List<Object[]> result = loanService.aggregateByLender();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/aggregate/interest")
    public ResponseEntity<List<Object[]>> aggregateByInterest() {
        List<Object[]> result = loanService.aggregateByInterest();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/aggregate/customer")
    public ResponseEntity<List<Object[]>> aggregateByCustomerId() {
        List<Object[]> result = loanService.aggregateByCustomerId();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
