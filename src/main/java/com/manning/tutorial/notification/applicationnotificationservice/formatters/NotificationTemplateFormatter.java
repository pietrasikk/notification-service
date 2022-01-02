package com.manning.tutorial.notification.applicationnotificationservice.formatters;

import com.manning.tutorial.notification.applicationnotificationservice.model.NotificationRequest;
import com.manning.tutorial.notification.applicationnotificationservice.model.notification.template.NotificationTemplateRequest;

public class NotificationTemplateFormatter {

    public NotificationTemplateRequest prepareTemplateRequest(NotificationRequest notificationRequest, String notificationMode) {
        return new NotificationTemplateRequest(notificationRequest.getNotificationParameters(), notificationRequest.getNotificationTemplateName(), notificationMode);
    }
}
