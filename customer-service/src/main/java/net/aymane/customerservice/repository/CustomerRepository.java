package net.aymane.customerservice.repository;

import net.aymane.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findCustomerByAuthId(String authId);

}
