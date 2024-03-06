package net.aymane.accountservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.aymane.accountservice.Mapper.BankAccountMapper;
import net.aymane.accountservice.clients.CustomerRestClient;
import net.aymane.accountservice.clients.OperationRestClient;

import net.aymane.accountservice.dto.BankAccountResponse;
import net.aymane.accountservice.exceptions.BalanceNotSufficentException;
import net.aymane.accountservice.exceptions.BankAccountNotFoundException;
import net.aymane.accountservice.model.Customer;
import net.aymane.accountservice.entities.BankAccount;
import net.aymane.accountservice.enums.AccountStatus;
import net.aymane.accountservice.enums.AccountType;
import net.aymane.accountservice.model.external.Operation;
import net.aymane.accountservice.model.external.dto.OperationResponseDto;
import net.aymane.accountservice.model.external.enums.OperationType;
import net.aymane.accountservice.model.external.exception.CustomerNotFoundException;
import net.aymane.accountservice.repository.BankAccountRepository;


import net.aymane.customerservice.requestDto.CustomerRequestDto;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import java.util.UUID;



@Service
@Slf4j
@Transactional
@AllArgsConstructor

public class AccountServiceImpl implements AccountService {
    private CustomerRestClient customerRestClient;
    private BankAccountRepository bankAccountRepository;
    private OperationRestClient operationRestClient;
    private BankAccountMapper bankAccountMapper;

    @Value("${spring.application.success}")
    private  String responseCodeSuccess;
    @Value("${spring.application.not_found}")
    private String responseCodeNotFound;

    @Override
    public void saveCustomer(CustomerRequestDto customerRequest) {
        log.info("customer created");
        customerRestClient.createCustomer(customerRequest);
    }

    @Override
    public Response updateStatus(String accountNumber, AccountStatus accountStatus) {

        return null;
    }
// ------------------------------------- create account -----------------------------------------------------------
    @Override
    public BankAccountResponse saveAccount(Double initialBalance, AccountType type, Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRestClient.findCustomerById(customerId);
        if (customer==null)throw new CustomerNotFoundException("customer not found");
        BankAccount bankAccount = BankAccount.builder()
                .accountId(UUID.randomUUID().toString())
                .balance(initialBalance)
                .type(type)
                .createAt(LocalDate.now()).status(AccountStatus.CREATED)
                .customer(customer)
                .customerId(customer.getId())
                .build();
              BankAccount savedBankaccount =  bankAccountRepository.save(bankAccount);
        return bankAccountMapper.fromBankAccountToResp(savedBankaccount);


    }
    //------------------------------------------ get Account by id --------------------------------------------------
    @Override
    public BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(()-> new BankAccountNotFoundException("account not found"));
        return bankAccount;
    }

    @Override
    public BankAccountResponse readAccountByAccountNumber(String accountNumber) {
        return null;
    }

    @Override
    public String getBalance(String accountNumber) {
        return null;
    }

    @Override
    public List<OperationResponseDto> getOperationFromAccountId(String id) {
        return null;
    }

    @Override
    public Response closeAccount(String accountNumber) {
        return null;
    }

    @Override
    public BankAccountResponse readAccountByCustomerId(String customerId) {
        return null;
    }



    @Override
    public List<Customer> listCustomer() {
        return null;
    }



    @Override
    public void debit(String accountId, Double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficentException {
        BankAccount bankAccount = getBankAccount(accountId);
        if (bankAccount.getBalance()<amount)throw new BalanceNotSufficentException("balance not sufficient");
        Operation operation = Operation.builder()
                .type(OperationType.DEBIT)
                .amount(amount)
                .description(description)
                .bankAccount(bankAccount)
                .bankAccount_id(Long.valueOf(bankAccount.getAccountId()))
                .build();
        operationRestClient.createOperation(operation);// need to solve it
        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepository.save(bankAccount);

    }

    @Override
    public void credit(String accountId, Double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount = getBankAccount(accountId);

        Operation operation = Operation.builder()
                .type(OperationType.DEBIT)
                .amount(amount)
                .description(description)
                .bankAccount(bankAccount)
                .bankAccount_id(Long.valueOf(bankAccount.getAccountId()))
                .build();
        operationRestClient.createOperation(operation);// need to solve it
        bankAccount.setBalance(bankAccount.getBalance()+amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfer(String accountSource, String accountDestination, Double amount) throws BankAccountNotFoundException, BalanceNotSufficentException {

        debit(accountSource,amount,"Transfer to"+accountDestination);
        credit(accountDestination,amount,"Transfer from"+ accountSource);

    }
}
