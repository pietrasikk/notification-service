package com.manning.tutorial.notification.applicationnotificationservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
public class NotificationRequest {

    String customerId;
    String notificationMode;
    List<NotificationParameter> notificationParameters;
    String notificationTemplateName;

    @JsonCreator
    NotificationRequest(@JsonProperty("customerId") String customerId,
                        @JsonProperty("notificationMode") String notificationMode,
                        @JsonProperty("notificationParameters") List<NotificationParameter> notificationParameters,
                        @JsonProperty("notificationTemplateName") String notificationTemplateName
    ) {
        this.customerId = customerId;
        this.notificationTemplateName = notificationTemplateName;
        this.notificationParameters = notificationParameters;
        this.notificationMode = notificationMode;
    }
}
