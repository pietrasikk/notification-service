package com.manning.tutorial.notification.applicationnotificationservice.model.notification.preferences;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class NotificationPreferencesResponse {

    String status;
    String statusDescription;
    boolean smsPreferenceFlag;
    boolean emailPreferenceFlag;
    String emailAddress;
    String phoneNumber;

    @JsonCreator
    public NotificationPreferencesResponse(@JsonProperty("status") String status,
                                @JsonProperty("statusDescription") String statusDescription,
                                @JsonProperty("smsPreferenceFlag") boolean smsPreferenceFlag,
                                @JsonProperty("emailPreferenceFlag") boolean emailPreferenceFlag,
                                @JsonProperty("emailAddress") String emailAddress,
                                @JsonProperty("phoneNumber") String phoneNumber) {
        this.status = status;
        this.statusDescription = statusDescription;
        this.smsPreferenceFlag = smsPreferenceFlag;
        this.emailPreferenceFlag = emailPreferenceFlag;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }
}
