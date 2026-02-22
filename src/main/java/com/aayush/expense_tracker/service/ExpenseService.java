package com.aayush.expense_tracker.service;
import com.aayush.expense_tracker.model.User;
import com.aayush.expense_tracker.model.Expense;
import com.aayush.expense_tracker.respostiory.ExpenseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository=expenseRepository;
    }
    public Expense addExpense(Expense expense, User user){
        expense.setUser(user);
        return expenseRepository.save(expense);
    }
}
