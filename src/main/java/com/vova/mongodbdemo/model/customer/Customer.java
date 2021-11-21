package com.vova.mongodbdemo.model.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vova.mongodbdemo.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Customer {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Address adress;
    private String email;
    @JsonIgnore
    private String password;

    public Customer(String firstName, String lastName, Address adress, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.email = email;
        this.password = password;
    }
}
