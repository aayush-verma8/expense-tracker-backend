package com.aayush.expense_tracker.model;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private String category;
    private String description;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
