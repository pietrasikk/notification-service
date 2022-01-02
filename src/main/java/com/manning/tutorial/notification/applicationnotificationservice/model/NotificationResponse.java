package com.manning.tutorial.notification.applicationnotificationservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class NotificationResponse {

    String status;
    String statusDescription;
    int notificationReferenceId;

    @JsonCreator
    public NotificationResponse(@JsonProperty("status") String status,
                                @JsonProperty("statusDescription") String statusDescription,
                                @JsonProperty("notificationReferenceId") int notificationReferenceId) {
        this.status = status;
        this.statusDescription = statusDescription;
        this.notificationReferenceId = notificationReferenceId;
    }
}
