package net.aymane.customerservice;

import net.aymane.customerservice.config.GlobalConfig;
import net.aymane.customerservice.entities.Customer;
import net.aymane.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties({GlobalConfig.class})
@EnableFeignClients
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}



	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		return args -> {

			List<Customer> customerList=List.of(
					Customer.builder()
							.firstName("Hassan")
							.lastName("Elhoumi")
							.email("hassan@gmail.com").password("123456").identificationNumber("y1142253").phoneNumber("0225826552200")
							.build(),
					Customer.builder()
							.identificationNumber("y445035")
							.firstName("Mohamed")
							.phoneNumber("1354136203")
							.lastName("Elhannaoui")
							.email("hassan@gmail.com")
							.password("123456")
							.build()

			);
			customerRepository.saveAll(customerList);
		};
	}

}
