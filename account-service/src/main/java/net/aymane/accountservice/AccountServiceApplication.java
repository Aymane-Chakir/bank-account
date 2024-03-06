package net.aymane.accountservice;

import net.aymane.accountservice.clients.CustomerRestClient;
import net.aymane.accountservice.entities.BankAccount;
import net.aymane.accountservice.enums.AccountStatus;
import net.aymane.accountservice.enums.AccountType;
import net.aymane.accountservice.repository.BankAccountRepository;
import net.aymane.customerservice.config.GlobalConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties({GlobalConfig.class})
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository accountRepository, CustomerRestClient customerRestClient){
        return args -> {
            customerRestClient.allCustomers().forEach(c->{
                BankAccount bankAccount1 = BankAccount.builder()
                        .accountId(UUID.randomUUID().toString())
                        .currency("MAD")
                        .balance(Math.random()*80000)
                        .createAt(LocalDate.now())
                        .type(AccountType.CURRENT_ACCOUNT)
                        .status(AccountStatus.CREATED)
                        .customerId(c.getId())
                        .build();
                BankAccount bankAccount2 = BankAccount.builder()
                        .accountId(UUID.randomUUID().toString())
                        .currency("MAD")
                        .balance(Math.random()*65432)
                        .createAt(LocalDate.now())
                        .type(AccountType.SAVING_ACCOUNT)
                        .status(AccountStatus.CREATED)
                        .customerId(c.getId())
                        .build();
                accountRepository.save(bankAccount1);
                accountRepository.save(bankAccount2);
            });


        };
    }

}
