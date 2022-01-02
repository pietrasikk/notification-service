package com.manning.tutorial.notification.applicationnotificationservice.model.notification.template;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.manning.tutorial.notification.applicationnotificationservice.model.NotificationParameter;
import lombok.Value;

import java.util.List;

@Value
public class NotificationTemplateRequest {

    List<NotificationParameter> notificationParameters;
    String notificationTemplateName;
    String notificationMode;

    @JsonCreator
    public NotificationTemplateRequest(@JsonProperty("notificationParameters") List<NotificationParameter> notificationParameters,
                                       @JsonProperty("notificationTemplateName") String notificationTemplateName,
                                       @JsonProperty("notificationMode") String notificationMode) {
        this.notificationParameters = notificationParameters;
        this.notificationTemplateName = notificationTemplateName;
        this.notificationMode = notificationMode;
    }
}
