package com.manning.tutorial.notification.applicationnotificationservice.model.notification.template;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class NotificationTemplateResponse {

    String status;
    String statusDescription;
    String emailContent;
    String smsContent;
    String emailSubject;

    @JsonCreator
    public NotificationTemplateResponse(@JsonProperty("status") String status,
                                        @JsonProperty("statusDescription") String statusDescription,
                                        @JsonProperty("emailContent") String emailContent,
                                        @JsonProperty("smsContent") String smsContent,
                                        @JsonProperty("emailSubject") String emailSubject) {
        this.status = status;
        this.statusDescription = statusDescription;
        this.emailContent = emailContent;
        this.smsContent = smsContent;
        this.emailSubject = emailSubject;
    }
}
