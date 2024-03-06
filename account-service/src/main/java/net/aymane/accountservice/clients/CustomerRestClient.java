package net.aymane.accountservice.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.aymane.accountservice.model.Customer;
import net.aymane.accountservice.model.external.dto.Response;
import net.aymane.customerservice.requestDto.CustomerRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultCustomer")
    Customer findCustomerById(@PathVariable Long id);
    @GetMapping("/customers")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getAllCustomers")
    List<Customer> allCustomers();

    @PostMapping("/customer")
    @CircuitBreaker(name = "customerService")
    Response createCustomer(CustomerRequestDto customerRequestDto);

    default Customer getDefaultCustomer(Long id, Exception exception){
        Customer customer=new Customer();
        customer.setId(id);
        customer.setFirstName("Not found");
        customer.setLastName("Not found");
        customer.setEmail("Not not found");
        return customer;
    }
    default List<Customer> getAllCustomers(Exception exception){
        return List.of();
    }
}
