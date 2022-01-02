package com.manning.tutorial.notification.applicationnotificationservice.model.notification.gateway;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class NotificationGatewayRequest {

    String customerId;
    String notificationMode;
    String notificationContent;
    String emailAddress;
    String emailSubject;
    String phoneNumber;

    @JsonCreator
    public NotificationGatewayRequest(@JsonProperty("customerId") String customerId,
                                      @JsonProperty("notificationMode") String notificationMode,
                                      @JsonProperty("notificationContent") String notificationContent,
                                      @JsonProperty("emailAddress") String emailAddress,
                                      @JsonProperty("emailSubject") String emailSubject,
                                      @JsonProperty("phoneNumber") String phoneNumber) {
        this.customerId = customerId;
        this.notificationMode = notificationMode;
        this.notificationContent = notificationContent;
        this.emailAddress = emailAddress;
        this.emailSubject = emailSubject;
        this.phoneNumber = phoneNumber;
    }
}
