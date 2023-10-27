package com.amsoft.microservice.notification;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class Notification {

    @SequenceGenerator(name = "notification_seq", sequenceName = "notification_seq")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_seq")
    private Long id;
    private String message;
    private String fromEmail;
    private String toEmail;
    private LocalDateTime createdAt;
    private Long customerId;

}
