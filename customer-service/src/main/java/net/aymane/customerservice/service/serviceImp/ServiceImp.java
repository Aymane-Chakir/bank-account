package net.aymane.customerservice.service.serviceImp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.aymane.customerservice.Exceptions.CustomerNotFoundException;
import net.aymane.customerservice.Mapper.CustomerMapper;
import net.aymane.customerservice.ResponseDto.CustomerResponseDto;
import net.aymane.customerservice.ResponseDto.Response;
import net.aymane.customerservice.client.AccountRestController;
import net.aymane.customerservice.entities.Customer;
import net.aymane.customerservice.model.BankAccount;
import net.aymane.customerservice.repository.CustomerRepository;
import net.aymane.customerservice.requestDto.CustomerRequestDto;
import net.aymane.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ServiceImp implements CustomerService {
//---------------------------- injection de dependence--------------------------
    private final CustomerRepository customerRepository;
//    private final AccountRestController accountRestController;
    private CustomerMapper customerMapper;
    private PasswordEncoder passwordEncoder;
//    @Value("${spring.application.success}")
//    private  String responseCodeSuccess;
//    @Value("${spring.application.not_found}")
//    private String responseCodeNotFound;

//------------------------ create a customer---------------------------------------
    @Override
    public Response createCustomer(CustomerRequestDto customerRequestDto) {
        log.info("Saving new customer");
        Customer customer = customerMapper.fromReqToCustomer(customerRequestDto);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);

        return Response.builder()
                .responseMessage("User created successfully")
                .build();
    }
//---------------------------- lister all customer--------------------------------------------------------------------
    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList.stream().map(customerMapper::fromCustomerToResp).collect(Collectors.toList());
    }
//-------------------------------- get customer by Auth_Id--------------------------------------------------
    @Override
    public CustomerResponseDto getCustomer(String authId) {
        Customer customer =customerRepository.findCustomerByAuthId(authId).orElseThrow(()->new RuntimeException(" customer not found on the server"));
        CustomerResponseDto customerResponseDto = customerMapper.fromCustomerToResp(customer);
        return customerResponseDto;
    }
//----------------------------------- Update customer--------------------------------------------------------------
    @Override
    public Response updateCustomer(Long id, CustomerRequestDto customerRequestDto) {
        Optional<Customer> customer = customerRepository.findById(customerRequestDto.getId());
        if (customer.isPresent()){
            Customer customer1 = customer.get();
            Customer updateCustomer = customerRepository.save(customer1);

            return Response.builder()
                    .responseCode("202")
                    .responseMessage("customer update successfully")
                    .build();
        }else {
            throw  new RuntimeException("invalid customer id");
        }
    }
// --------------------------------- get customer by id -----------------------------------------------
    @Override
    public CustomerResponseDto getCustomerById(Long id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException("not found"));
        return customerMapper.fromCustomerToResp(customer);
    }
//--------------------------- get customer by account_id---------------------------------------------------
//    @Override
//    public CustomerResponseDto getCustomerByAccountId(String accountId) {
//        ResponseEntity<BankAccount> response = accountRestController.getByAccountNumber(accountId);
//        if(Objects.isNull(response.getBody())){
//            throw new RuntimeException("account not found on the server");
//        }
//        Long customerId = response.getBody().getCustomerId();
//        return customerRepository.findById(customerId)
//                .map(customer ->customerMapper.fromCustomerToResp(customer))
//                .orElseThrow(() -> new RuntimeException("User not found on the server"));
//    }
}
