package net.aymane.accountservice.entities;

import jakarta.persistence.*;
import lombok.*;
import net.aymane.accountservice.enums.AccountStatus;
import net.aymane.accountservice.enums.AccountType;
import net.aymane.accountservice.model.Customer;

import java.time.LocalDate;
@Entity
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    private String accountId;
    private Double balance;
    private LocalDate createAt;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @Transient
    private Customer customer;
    private Long customerId;
}
