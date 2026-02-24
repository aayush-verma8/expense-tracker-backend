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
    public List<Expense>getUserExpenses(User user){
        return expenseRepository.findByUser(user);
    }
    public void deleteExpense(Long expenseId,User user){
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(()->new RuntimeException("Expense not found"));
        if(!expense.getUser().getId().equals(user.getId())){
            throw new RuntimeException("Unauthorized to delete this expense");
        }
        expenseRepository.delete(expense);
    }
    public Expense updateExpense(Long expenseId, Expense updatedExpense, User user) {

        Expense existingExpense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (!existingExpense.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized to update this expense");
        }

        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setCategory(updatedExpense.getCategory());
        existingExpense.setDescription(updatedExpense.getDescription());
        existingExpense.setDate(updatedExpense.getDate());

        return expenseRepository.save(existingExpense);
    }
}
