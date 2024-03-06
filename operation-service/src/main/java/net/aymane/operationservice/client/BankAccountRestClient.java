package net.aymane.operationservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.aymane.operationservice.model.AccountType;
import net.aymane.operationservice.model.BankAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ACCOUNT-SERVICE")
public interface BankAccountRestClient {
    @GetMapping("/account/{id}")
    @CircuitBreaker(name = "accountService",fallbackMethod = "getDefaultAccount")
    BankAccount findCustomerById(@PathVariable String id);
    @CircuitBreaker(name = "accountService",fallbackMethod = "getAllAccounts")
    @GetMapping("/accounts")
    List<BankAccount> accountList();

    default BankAccount getDefaultAccount(String id, Exception exception){
        BankAccount bankAccount=BankAccount.builder()
                .accountId(id)
                .balance(00.00)
                .type(AccountType.SAVING_ACCOUNT)
                .createAt(null)
                .currency("MAD")
                .build();

        return bankAccount;
    }

    default List<BankAccount> getAllAccounts(Exception exception){
        return List.of();
    }
}
