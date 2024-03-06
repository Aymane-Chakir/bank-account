package net.aymane.accountservice.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.aymane.accountservice.config.FeignConfiguration;

import net.aymane.accountservice.model.external.Operation;

import net.aymane.accountservice.model.external.dto.OperationResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "OPERATION-SERVICE",configuration = FeignConfiguration.class)
public interface OperationRestClient {
    @GetMapping("/operation/{id}")
    @CircuitBreaker(name = "operationService", fallbackMethod = "getDefaultOperation")
    OperationResponseDto findCustomerById(@PathVariable Long id);

    @CircuitBreaker(name = "operationService", fallbackMethod = "getAllOperations")
    @GetMapping("/operations")
    List<OperationResponseDto> getTransactionsFromAccountId(@RequestParam String accountId);
    @PostMapping("/operation")
    @CircuitBreaker(name = "operationService", fallbackMethod = "getDefaultOperation")
    void createOperation(@RequestParam Operation operation);

    default OperationResponseDto getDefaultOperation(Long id, Exception exception){
      OperationResponseDto operation = new OperationResponseDto();
      operation.setAmount(null);
      operation.setDate(null);
      operation.setType(null);
        return operation;
    }
    default List<OperationResponseDto> getAllOperations(Exception exception){
        return List.of();
    }
}
