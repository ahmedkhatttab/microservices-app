package com.amsoft.microservice.customer;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;
    @PostMapping
    public void registerCustomer(@RequestBody CustomerDto customerDto){
        log.info("Customer: {}", customerDto);
        customerService.registerCustomer(customerDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(customerService.findCustomerById(id));
    }


    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

}
