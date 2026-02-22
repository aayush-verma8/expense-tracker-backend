package com.aayush.expense_tracker.respostiory;

import com.aayush.expense_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserRespostiory extends JpaRepository<User,Long> {
    Optional<User>findByEmail(String email);
    boolean existsByEmail(String email);
}
