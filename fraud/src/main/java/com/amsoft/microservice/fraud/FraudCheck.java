package com.amsoft.microservice.fraud;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_check")
@NoArgsConstructor
@Getter
@Setter
public class FraudCheck {

    @Id
    @SequenceGenerator(name = "fraud_seq", sequenceName = "fraud_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fraud_seq")
    private Long id;
    private Long customerId;
    private Boolean isFraudster;
    private LocalDateTime createdAt;

}
