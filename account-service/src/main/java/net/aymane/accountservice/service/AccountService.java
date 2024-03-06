package net.aymane.accountservice.service;

import net.aymane.accountservice.dto.BankAccountRequest;
import net.aymane.accountservice.dto.BankAccountResponse;

import net.aymane.accountservice.entities.BankAccount;
import net.aymane.accountservice.enums.AccountStatus;
import net.aymane.accountservice.enums.AccountType;
import net.aymane.accountservice.exceptions.BalanceNotSufficentException;
import net.aymane.accountservice.exceptions.BankAccountNotFoundException;
import net.aymane.accountservice.model.external.dto.OperationResponseDto;
import net.aymane.accountservice.model.Customer;
import net.aymane.accountservice.model.external.exception.CustomerNotFoundException;
import net.aymane.customerservice.requestDto.CustomerRequestDto;
import org.apache.coyote.Response;

import java.util.List;

public interface AccountService {




     void saveCustomer(CustomerRequestDto customerRequest);
     /**
      * Creates a new account.
      *
      *  the account information to be created
      * @return the response containing the created account
      */

     /**
      * Updates the status of an account.
      *
,      * @param accountUpdate   The account status update to apply.
      * @return                The response indicating the success or failure of the update.
      */
     Response updateStatus (String accountNumber, AccountStatus accountStatus);
    BankAccountResponse saveAccount(Double initialBalance, AccountType type, Long customerId) throws CustomerNotFoundException;
     BankAccountResponse readAccountByAccountNumber (String accountNumber);
     String getBalance(String accountNumber);
     List<OperationResponseDto> getOperationFromAccountId(String id);
     Response closeAccount(String accountNumber);
     BankAccountResponse readAccountByCustomerId(String customerId);
     List<Customer> listCustomer();
     BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException;

     void debit (String accountId,Double amount,String description) throws BankAccountNotFoundException, BalanceNotSufficentException;

     void credit(String accountId,Double amount,String description) throws BankAccountNotFoundException;

     void transfer(String accountSource,String accountDestination , Double amount) throws BankAccountNotFoundException, BalanceNotSufficentException;
}
