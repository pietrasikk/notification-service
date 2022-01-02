package com.manning.tutorial.notification.applicationnotificationservice.services.notification;

import com.manning.tutorial.notification.applicationnotificationservice.exceptions.NotificationException;
import com.manning.tutorial.notification.applicationnotificationservice.formatters.NotificationGatewayFormatter;
import com.manning.tutorial.notification.applicationnotificationservice.integration.NotificationGatewayIntegration;
import com.manning.tutorial.notification.applicationnotificationservice.model.NotificationRequest;
import com.manning.tutorial.notification.applicationnotificationservice.model.notification.gateway.NotificationGatewayRequest;
import com.manning.tutorial.notification.applicationnotificationservice.model.notification.gateway.NotificationGatewayResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotificationGatewayService {

    private final String MODULE_NAME = "Notification Gateway";
    private final NotificationGatewayIntegration notificationGatewayIntegration;
    private final NotificationGatewayFormatter notificationGatewayFormatter;

    public NotificationGatewayResponse sendNotificationToGateway(NotificationRequest notificationRequest, String notificationMode, String notificationContent, String emailAddress, String emailSubject, String phoneNumber) {
        NotificationGatewayRequest notificationGatewayRequest = notificationGatewayFormatter.prepareNotificationGatewayRequest(notificationRequest, notificationMode, notificationContent, emailAddress, emailSubject, phoneNumber);
        NotificationGatewayResponse notificationGatewayResponse = notificationGatewayIntegration.sendNotificationTemplate(notificationGatewayRequest);
        if (notificationGatewayResponse.getStatus().equals("SUCCESS")) {
            return notificationGatewayResponse;
        } else {
            throw new NotificationException(MODULE_NAME);
        }
    }
}
