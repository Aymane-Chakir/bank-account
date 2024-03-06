package net.aymane.customerservice.service;

import net.aymane.customerservice.Exceptions.CustomerNotFoundException;
import net.aymane.customerservice.ResponseDto.CustomerResponseDto;
import net.aymane.customerservice.ResponseDto.Response;
import net.aymane.customerservice.requestDto.CustomerRequestDto;

import java.util.List;

public interface CustomerService {
    Response createCustomer (CustomerRequestDto customerRequestDto);
    List<CustomerResponseDto> getAllCustomers();

    CustomerResponseDto getCustomer(String authId);

    Response updateCustomer(Long id, CustomerRequestDto customerRequestDto);
    CustomerResponseDto getCustomerById(Long id) throws CustomerNotFoundException;

//    CustomerResponseDto getCustomerByAccountId(String accountId);

}
