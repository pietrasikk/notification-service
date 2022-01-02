package com.manning.tutorial.notification.applicationnotificationservice.integration;

import com.manning.tutorial.notification.applicationnotificationservice.model.notification.template.NotificationTemplateRequest;
import com.manning.tutorial.notification.applicationnotificationservice.model.notification.template.NotificationTemplateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class NotificationTemplateIntegration {

    @Value("${notification.template.url}")
    private String URL;
    private final RestTemplate restTemplate;

    public NotificationTemplateResponse getNotificationTemplate(NotificationTemplateRequest notificationTemplateRequest) {
        ResponseEntity<NotificationTemplateResponse> notificationTemplateResponseResponseEntity = restTemplate.postForEntity(URL, notificationTemplateRequest, NotificationTemplateResponse.class);
        return notificationTemplateResponseResponseEntity.getBody();
    }
}
