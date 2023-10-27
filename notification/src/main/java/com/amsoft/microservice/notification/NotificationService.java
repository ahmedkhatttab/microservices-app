package com.amsoft.microservice.notification;


import com.amsoft.microservice.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepo notificationRepo;


    public void createNotification(NotificationRequest notificationRequest){
        Notification notification = new Notification();
        notification.setToEmail(notificationRequest.to());
        notification.setFromEmail(notificationRequest.from());
        notification.setMessage(notificationRequest.message());
        notification.setCreatedAt(notificationRequest.createAt());
        notification.setCustomerId(notificationRequest.customerId());
        notificationRepo.save(notification);
    }


}
