package com.amsoft.microservice.fraud;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class FraudService {

    private final FraudRepo fraudRepo;

    public boolean isFraudster(Long customerId){
        FraudCheck fraudCheck = new FraudCheck();
        fraudCheck.setCustomerId(customerId);
        fraudCheck.setIsFraudster(false);
        fraudCheck.setCreatedAt(LocalDateTime.now());
        fraudRepo.save(fraudCheck);

        return fraudCheck.getIsFraudster();
    }

}
