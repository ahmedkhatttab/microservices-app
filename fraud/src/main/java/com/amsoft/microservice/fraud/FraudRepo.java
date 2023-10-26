package com.amsoft.microservice.fraud;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudRepo extends JpaRepository<FraudCheck, Long> {
}
