package net.aymane.customerservice.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerRequestDto {


    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String identificationNumber;
    private String authId;
}
