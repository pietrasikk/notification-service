package com.manning.tutorial.notification.applicationnotificationservice.formatters;

import com.manning.tutorial.notification.applicationnotificationservice.model.NotificationRequest;
import com.manning.tutorial.notification.applicationnotificationservice.model.notification.gateway.NotificationGatewayRequest;

public class NotificationGatewayFormatter {

    public NotificationGatewayRequest prepareNotificationGatewayRequest(NotificationRequest notificationRequest,
                                                                        String notificationMode,
                                                                        String notificationContent,
                                                                        String emailAddress,
                                                                        String emailSubject,
                                                                        String phoneNumber) {
        return new NotificationGatewayRequest(notificationRequest.getCustomerId(), notificationMode, notificationContent, emailAddress, emailSubject, phoneNumber);
    }
}
