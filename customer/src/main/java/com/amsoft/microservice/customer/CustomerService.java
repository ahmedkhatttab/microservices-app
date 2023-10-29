package com.amsoft.microservice.customer;

import com.amsoft.microservice.clients.fraud.FraudCheckResponse;
import com.amsoft.microservice.clients.fraud.FraudClient;
import com.amsoft.microservice.clients.notification.NotificationClient;
import com.amsoft.microservice.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepo customerRepo;
//    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setFirstName(customerDto.firstName());
        customer.setLastName(customerDto.lastName());
        customer.setEmail(customerDto.email());
        // use saveAndFlush to get id
        customerRepo.saveAndFlush(customer);

        // check fraud service
        FraudCheckResponse response = fraudClient.checkFraudster(customer.getId());

//        FraudCheckResponse response = restTemplate.getForObject(
//                "http://FRAUD/api/v1/fraud-check/{id}",
//                FraudCheckResponse.class,
//                customer.getId()
//        );

        if(response.isFraudster()){
            throw new RuntimeException("Fraud Customer Exception");
        }

        // send notification service
        NotificationRequest notificationRequest = new NotificationRequest(
            customerDto.email(),
            String.format("Thanks %s %s, for registering with us", customerDto.firstName(), customerDto.lastName()),
            customer.getId()
        );

        notificationClient.createNotification(notificationRequest);

    }


    List<CustomerDto> findAllCustomers(){
        List<Customer> customerList = customerRepo.findAll();
        return customerList.stream().map(customer -> mapToDto(customer)).collect(Collectors.toList());
    }


    CustomerDto findCustomerById(Long id){
        Customer customer = customerRepo.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
        return mapToDto(customer);
    }

    private CustomerDto mapToDto(Customer customer){
        return new CustomerDto(customer.getFirstName(), customer.getLastName(), customer.getEmail());
    }

}
