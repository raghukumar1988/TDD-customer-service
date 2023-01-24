package com.org.comcast.customerservice.api.controller;

import com.org.comcast.customerservice.api.dto.CustomerDTO;
import com.org.comcast.customerservice.api.model.Customer;
import com.org.comcast.customerservice.api.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")  // TDD Added this mapping to fix http 404 error
public class CustomerController {

    // Step 5  -- Injecting Service Bean
    private CustomerService customerService;

    //@Autowired
    /* As of Spring 4.3, classes with a single constructor can omit the @Autowired annotation. */
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ResponseStatus(HttpStatus.CREATED)   // TDD added this line to match expected http 201
    @PostMapping("/createCustomer")  // TDD Added this mapping to fix http 404 error
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = Customer.builder()
                .customerId(customerDTO.getCustomerId())
                .customerName(customerDTO.getCustomerName())
                .activationStatus(customerDTO.isActivationStatus())
                .gender(customerDTO.getGender())
                .build();
        Customer savedCustomer = customerService.save(customer);
        return CustomerDTO.builder()
                .customerId(savedCustomer.getCustomerId())
                .customerName(savedCustomer.getCustomerName())
                .activationStatus(savedCustomer.isActivationStatus())
                .gender(savedCustomer.getGender())
                .build();
    }

}
