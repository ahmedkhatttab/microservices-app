package com.amsoft.microservice.fraud;

import com.amsoft.microservice.clients.fraud.FraudCheckResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/fraud-check")
public class FraudController {

    private final FraudService fraudService;

    @GetMapping("/{customerId}")
    public ResponseEntity<FraudCheckResponse> checkFraudster(@PathVariable Long customerId){
        return ResponseEntity.ok(
                new FraudCheckResponse(fraudService.isFraudster(customerId))
        );
    }

}
