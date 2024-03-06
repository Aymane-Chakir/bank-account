package net.aymane.customerservice.web;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.aymane.customerservice.Exceptions.CustomerNotFoundException;
import net.aymane.customerservice.Mapper.CustomerMapper;
import net.aymane.customerservice.ResponseDto.CustomerResponseDto;
import net.aymane.customerservice.ResponseDto.Response;
import net.aymane.customerservice.entities.Customer;
import net.aymane.customerservice.repository.CustomerRepository;
import net.aymane.customerservice.requestDto.CustomerRequestDto;
import net.aymane.customerservice.service.CustomerService;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Slf4j

@AllArgsConstructor
public class CustomerRestController {
    private final CustomerService customerService;

// ----------------------------- add new customer--------------------------------------------------------
    @PostMapping("/register")
    public ResponseEntity<Response> createCustomer (@RequestParam CustomerRequestDto customerRequestDto){
        log.info("creating user with :{}",customerRequestDto.toString());
        return ResponseEntity.ok(customerService.createCustomer(customerRequestDto));
    };
//------------------------------ lister all customer -----------------------------------------------------
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponseDto>> customerList(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
// ----------------------------- get customer by id ------------------------------------------------------
    @GetMapping("/customer/{id}")

    public ResponseEntity<CustomerResponseDto> customerById(@PathVariable Long id) throws CustomerNotFoundException {
       log.info("reading customer id");
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
// ------------------------------ update customer-----------------------------------------------------------
    public ResponseEntity<Response> updateCustomer (@PathVariable Long id , CustomerRequestDto customerRequestDto){
      return ResponseEntity.ok(customerService.updateCustomer(id,customerRequestDto));
    };

// ------------------------------ check session--------------------------------------------------------------
//@GetMapping("/mySession")
//    public Authentication authentication(Authentication authentication){
//        return authentication;
//    }


}
