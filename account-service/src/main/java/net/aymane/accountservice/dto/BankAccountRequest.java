package net.aymane.accountservice.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import net.aymane.accountservice.enums.AccountStatus;
import net.aymane.accountservice.enums.AccountType;

import java.time.LocalDate;

@Getter
@Setter
public class BankAccountRequest {
    private String accountId;
    private Double balance;
    private LocalDate createAt;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
}
