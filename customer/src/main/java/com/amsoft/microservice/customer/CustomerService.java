package com.amsoft.microservice.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepo customerRepo;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setFirstName(customerDto.firstName());
        customer.setLastName(customerDto.lastName());
        customer.setEmail(customerDto.email());
        // use saveAndFlush to get id
        customerRepo.saveAndFlush(customer);

        FraudCheckResponse response = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{id}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if(response.isFraudster()){
            throw new RuntimeException("Fraud Customer Exception");
        }

    }

}
