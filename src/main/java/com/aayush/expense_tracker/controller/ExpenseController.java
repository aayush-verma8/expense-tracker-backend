package com.aayush.expense_tracker.controller;
import com.aayush.expense_tracker.respostiory.UserRepository;
import com.aayush.expense_tracker.model.User;
import com.aayush.expense_tracker.model.Expense;
import com.aayush.expense_tracker.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")

public class ExpenseController {
    private final ExpenseService expenseService;
    private final UserRepository userRepository;
    public ExpenseController(ExpenseService expenseService, UserRepository userRepository) {
        this.expenseService = expenseService;
        this.userRepository = userRepository;
    }
    @PostMapping
    public ResponseEntity<Expense> addExpense(
            @RequestBody Expense expense,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        Expense savedExpense = expenseService.addExpense(expense, user);

        return ResponseEntity.status(201).body(savedExpense);
    }
    @GetMapping
    public ResponseEntity<List<Expense>> getUserExpenses(
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        List<Expense> expenses = expenseService.getUserExpenses(user);

        return ResponseEntity.ok(expenses);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(
            @PathVariable Long id,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        expenseService.deleteExpense(id, user);

        return ResponseEntity.ok("Expense deleted successfully");
    }
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(
            @PathVariable Long id,
            @RequestBody Expense expense,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        Expense updated = expenseService.updateExpense(id, expense, user);

        return ResponseEntity.ok(updated);
    }
}
