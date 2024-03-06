package net.aymane.operationservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.aymane.operationservice.enums.OperationType;
import net.aymane.operationservice.model.BankAccount;

import java.time.LocalDate;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String amount;
    private String transactionStatus;
    private String description;
    private String comments;
    @Enumerated(EnumType.STRING)
    private OperationType type;
    @Transient
    private BankAccount bankAccount;
    private Long bankAccount_id;
}
