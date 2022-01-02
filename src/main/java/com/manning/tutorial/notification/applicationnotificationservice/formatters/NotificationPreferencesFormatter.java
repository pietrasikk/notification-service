package com.manning.tutorial.notification.applicationnotificationservice.formatters;

import com.manning.tutorial.notification.applicationnotificationservice.model.notification.preferences.NotificationPreferencesRequest;

public class NotificationPreferencesFormatter {

    public NotificationPreferencesRequest prepareNotificationPreferencesRequest(String customerId) {
        return new NotificationPreferencesRequest(customerId);
    }
}
