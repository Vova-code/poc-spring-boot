package com.vova.mongodbdemo.service.customer;

import com.vova.mongodbdemo.model.customer.Customer;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties;

import javax.servlet.http.HttpSession;

public interface CustomerService {

    Customer add(Customer customer);
    Customer update(Customer customer);
    Customer findByEmail(String email);
    boolean isEmailUsed(String email);
}
