package net.aymane.accountservice.web;

import net.aymane.accountservice.clients.CustomerRestClient;
import net.aymane.accountservice.entities.BankAccount;
import net.aymane.accountservice.model.Customer;
import net.aymane.accountservice.repository.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository accountRepository;
    private CustomerRestClient customerRestClient;

    public AccountRestController(BankAccountRepository accountRepository,
                                 CustomerRestClient customerRestClient) {
        this.accountRepository = accountRepository;
        this.customerRestClient = customerRestClient;
    }
    @GetMapping("/accounts")
    public List<BankAccount> accountList(){
        List<BankAccount> accountList = accountRepository.findAll();
        accountList.forEach(acc->{
            acc.setCustomer(customerRestClient.findCustomerById(acc.  getCustomerId()));
        });
        return accountList;
    }
    @GetMapping("/account/{id}")
    public BankAccount bankAccountById(@PathVariable String id){
        BankAccount bankAccount= accountRepository.findById(id).get();
        Customer customer=customerRestClient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }
}
