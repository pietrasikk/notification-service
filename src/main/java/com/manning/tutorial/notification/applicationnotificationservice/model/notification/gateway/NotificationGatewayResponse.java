package com.manning.tutorial.notification.applicationnotificationservice.model.notification.gateway;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class NotificationGatewayResponse {

    String status;
    String statusDescription;

    @JsonCreator
    public NotificationGatewayResponse(@JsonProperty("status") String status,
                                       @JsonProperty("statusDescription") String statusDescription) {
        this.status = status;
        this.statusDescription = statusDescription;
    }
}
