package net.aymane.accountservice.model.external;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.aymane.accountservice.entities.BankAccount;
import net.aymane.accountservice.model.external.enums.OperationType;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
public class Operation {
    private Long id;
    private LocalDate date;
    private Double amount;
    private String transactionStatus;
    private String comments;
    private String description;
    private OperationType type;
    private BankAccount bankAccount;
    private Long bankAccount_id;
}
