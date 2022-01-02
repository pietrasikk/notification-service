package com.manning.tutorial.notification.applicationnotificationservice.model.notification.preferences;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class NotificationPreferencesRequest {

    String customerId;

    @JsonCreator
    public NotificationPreferencesRequest(@JsonProperty("customerId") String customerId) {
        this.customerId = customerId;
    }
}
