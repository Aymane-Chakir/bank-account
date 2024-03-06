package net.aymane.customerservice.Mapper;

import net.aymane.customerservice.ResponseDto.CustomerResponseDto;
import net.aymane.customerservice.entities.Customer;
import net.aymane.customerservice.requestDto.CustomerRequestDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerResponseDto fromCustomerToResp(Customer customer){
      CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        BeanUtils.copyProperties(customer,customerResponseDto);
        return customerResponseDto;
    };


    public Customer fromReqToCustomer(CustomerRequestDto customerRequestDto){
        Customer customer=new Customer();
        BeanUtils.copyProperties(customerRequestDto,customer);
        return customer;
    };





}
