package com.manning.tutorial.notification.applicationnotificationservice.services.notification;

import com.manning.tutorial.notification.applicationnotificationservice.exceptions.NotificationException;
import com.manning.tutorial.notification.applicationnotificationservice.formatters.NotificationTemplateFormatter;
import com.manning.tutorial.notification.applicationnotificationservice.integration.NotificationTemplateIntegration;
import com.manning.tutorial.notification.applicationnotificationservice.model.NotificationRequest;
import com.manning.tutorial.notification.applicationnotificationservice.model.notification.template.NotificationTemplateRequest;
import com.manning.tutorial.notification.applicationnotificationservice.model.notification.template.NotificationTemplateResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotificationTemplateService {

    private final String MODULE_NAME = "Notification Template";
    private final NotificationTemplateIntegration notificationTemplateIntegration;
    private final NotificationTemplateFormatter notificationTemplateFormatter;

    public NotificationTemplateResponse getNotificationTemplate(NotificationRequest notificationRequest, String notificationMode) {
        NotificationTemplateRequest notificationTemplateRequest = notificationTemplateFormatter.prepareTemplateRequest(notificationRequest, notificationMode);
        NotificationTemplateResponse notificationTemplate = notificationTemplateIntegration.getNotificationTemplate(notificationTemplateRequest);
        if (notificationTemplate.getStatus().equals("SUCCESS")) {
            return notificationTemplate;
        } else {
            throw new NotificationException(MODULE_NAME);
        }
    }
}
