package com.technicaltransition.ratelimiter.repository;

import java.util.List;

import com.technicaltransition.ratelimiter.modal.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    Customer findById(long id);
}
