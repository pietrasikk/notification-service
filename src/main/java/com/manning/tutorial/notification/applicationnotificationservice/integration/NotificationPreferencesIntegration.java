package com.manning.tutorial.notification.applicationnotificationservice.integration;

import com.manning.tutorial.notification.applicationnotificationservice.model.notification.preferences.NotificationPreferencesRequest;
import com.manning.tutorial.notification.applicationnotificationservice.model.notification.preferences.NotificationPreferencesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class NotificationPreferencesIntegration {

    @Value("${notification.preferences.url}")
    private String URL;
    private final RestTemplate restTemplate;

    public NotificationPreferencesResponse getNotificationPreferences(NotificationPreferencesRequest notificationPreferencesRequest) {
        ResponseEntity<NotificationPreferencesResponse> notificationPreferencesResponseResponseEntity = restTemplate.postForEntity(URL, notificationPreferencesRequest, NotificationPreferencesResponse.class);
        return notificationPreferencesResponseResponseEntity.getBody();
    }
}
