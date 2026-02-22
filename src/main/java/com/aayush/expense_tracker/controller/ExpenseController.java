package com.aayush.expense_tracker.controller;
import com.aayush.expense_tracker.respostiory.UserRepository;
import com.aayush.expense_tracker.model.User;
import com.aayush.expense_tracker.model.Expense;
import com.aayush.expense_tracker.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
            @RequestHeader("user-email") String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Expense savedExpense = expenseService.addExpense(expense, user);

        return ResponseEntity.status(201).body(savedExpense);
    }
}
