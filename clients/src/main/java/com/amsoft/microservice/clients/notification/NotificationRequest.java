package com.amsoft.microservice.clients.notification;

import java.time.LocalDateTime;

public record NotificationRequest(String from, String to, String message, LocalDateTime createAt, Long customerId) {

    private final static String FROM = "eng.ahmedkhatab@gmail.com";
    private final static LocalDateTime CREATED_AT = LocalDateTime.now();

    public NotificationRequest(String to, String message, Long customerId){
        this(FROM, to, message, CREATED_AT, customerId);
    }

}
