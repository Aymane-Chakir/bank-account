package net.aymane.accountservice.dto;

import lombok.Getter;
import lombok.Setter;
import net.aymane.accountservice.enums.AccountType;

@Getter @Setter
public class BankAccountResponse {
    private String accountId;
    private double balance;
    private String currency;
    private AccountType type;

}
