package com.org.comcast.customerservice.api.controller;

import com.org.comcast.customerservice.api.dto.CustomerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")  // TDD Added this mapping to fix http 404 error
public class CustomerController {

    @ResponseStatus(HttpStatus.CREATED)   // TDD added this line to match expected http 201
    @PostMapping("/createCustomer")  // TDD Added this mapping to fix http 404 error
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        return CustomerDTO // building response from RequestBody
                .builder()
                .customerId(customerDTO.getCustomerId())
                .customerName(customerDTO.getCustomerName())
                .activationStatus(customerDTO.isActivationStatus())
                .gender(customerDTO.getGender())
                .build();
    }

}
