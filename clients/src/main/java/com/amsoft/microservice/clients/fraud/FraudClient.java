package com.amsoft.microservice.clients.fraud;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "fraud", path = "api/v1/fraud-check")
public interface FraudClient {

    @GetMapping("/{customerId}")
    FraudCheckResponse checkFraudster(@PathVariable("customerId") Long customerId);
}
