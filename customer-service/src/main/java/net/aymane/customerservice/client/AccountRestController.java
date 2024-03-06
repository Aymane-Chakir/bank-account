package net.aymane.customerservice.client;

import net.aymane.customerservice.model.BankAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "account-service")
public interface AccountRestController {
    ResponseEntity<BankAccount> getByAccountNumber(@RequestParam String accountNumber);
}
