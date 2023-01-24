package com.org.comcast.customerservice.api.controller;

import com.org.comcast.customerservice.api.dto.CustomerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @RequestMapping("/createCustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createCustomer() {
        return CustomerDTO.builder().customerId(979053L).customerName("Raghu").activationStatus(true).gender("Male").build();
    }

}
