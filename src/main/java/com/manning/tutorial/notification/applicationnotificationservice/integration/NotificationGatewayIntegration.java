package com.manning.tutorial.notification.applicationnotificationservice.integration;

import com.manning.tutorial.notification.applicationnotificationservice.model.notification.gateway.NotificationGatewayRequest;
import com.manning.tutorial.notification.applicationnotificationservice.model.notification.gateway.NotificationGatewayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class NotificationGatewayIntegration {

    @Value("${notification.gateway.url}")
    private String URL;
    private final RestTemplate restTemplate;

    public NotificationGatewayResponse sendNotificationTemplate(NotificationGatewayRequest notificationGatewayRequest) {
        ResponseEntity<NotificationGatewayResponse> notificationGatewayResponseResponseEntity = restTemplate.postForEntity(URL, notificationGatewayRequest, NotificationGatewayResponse.class);
        return notificationGatewayResponseResponseEntity.getBody();
    }
}
