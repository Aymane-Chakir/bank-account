package net.aymane.customerservice.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.aymane.customerservice.entities.Customer;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
public class BankAccount {
    private String accountId;
    private Double balance;
    private LocalDate createAt;
    private String currency;
    private AccountType type;
    private AccountStatus status;
    private Customer customer;
    private Long customerId;
}
