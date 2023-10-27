package com.amsoft.microservice.customer;

import com.amsoft.microservice.clients.fraud.FraudCheckResponse;
import com.amsoft.microservice.clients.fraud.FraudClient;
import com.amsoft.microservice.clients.notification.NotificationClient;
import com.amsoft.microservice.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

}
