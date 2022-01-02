package com.manning.tutorial.notification.applicationnotificationservice.services.notification;

import com.manning.tutorial.notification.applicationnotificationservice.exceptions.NotificationException;
import com.manning.tutorial.notification.applicationnotificationservice.formatters.NotificationPreferencesFormatter;
import com.manning.tutorial.notification.applicationnotificationservice.integration.NotificationPreferencesIntegration;
import com.manning.tutorial.notification.applicationnotificationservice.model.notification.preferences.NotificationPreferencesRequest;
import com.manning.tutorial.notification.applicationnotificationservice.model.notification.preferences.NotificationPreferencesResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotificationPreferencesService {

    private final String MODULE_NAME = "Notification Preferences";
    private final NotificationPreferencesIntegration notificationPreferencesIntegration;
    private final NotificationPreferencesFormatter notificationPreferencesFormatter;

    public NotificationPreferencesResponse getUserNotificationPreferences(String customerId) {
        NotificationPreferencesRequest notificationPreferencesRequest = notificationPreferencesFormatter.prepareNotificationPreferencesRequest(customerId);
        NotificationPreferencesResponse notificationPreferences = notificationPreferencesIntegration.getNotificationPreferences(notificationPreferencesRequest);
        if (notificationPreferences.getStatus().equals("SUCCESS")) {
            return notificationPreferences;
        } else {
            throw new NotificationException(MODULE_NAME);
        }
    }
}
