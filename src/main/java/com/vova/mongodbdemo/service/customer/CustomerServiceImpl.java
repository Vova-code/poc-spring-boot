package com.vova.mongodbdemo.service.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vova.mongodbdemo.dao.customer.CustomerDao;
import com.vova.mongodbdemo.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceImpl implements CustomerService{

    ObjectMapper mapper = new ObjectMapper();

    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Customer add(Customer customer) {
        return customerDao.insert(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerDao.findByEmail(email);
    }

    @Override
    public boolean isEmailUsed(String email) {
        return customerDao.existsByEmail(email);
    }
}
