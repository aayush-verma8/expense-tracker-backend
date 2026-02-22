package com.aayush.expense_tracker.respostiory;
import com.aayush.expense_tracker.model.User;
import com.aayush.expense_tracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Long>{
    List<Expense> findByUser(User user);
}
