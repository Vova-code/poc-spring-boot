package com.vova.mongodbdemo.dao.customer;

import com.vova.mongodbdemo.model.customer.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerDao extends MongoRepository<Customer, String> {

    Customer insert(Customer customer);
    Customer save(Customer customer);
    Optional<Customer> findById(String id);
    Customer findByEmail(String email);
    boolean existsByEmail(String email);
}
