package com.manning.tutorial.notification.applicationnotificationservice.services;

import com.manning.tutorial.notification.applicationnotificationservice.entities.NotificationEntity;
import com.manning.tutorial.notification.applicationnotificationservice.model.NotificationRequest;
import com.manning.tutorial.notification.applicationnotificationservice.model.NotificationResponse;
import com.manning.tutorial.notification.applicationnotificationservice.model.notification.preferences.NotificationPreferencesResponse;
import com.manning.tutorial.notification.applicationnotificationservice.model.notification.template.NotificationTemplateResponse;
import com.manning.tutorial.notification.applicationnotificationservice.repositories.NotificationRepository;
import com.manning.tutorial.notification.applicationnotificationservice.services.notification.NotificationGatewayService;
import com.manning.tutorial.notification.applicationnotificationservice.services.notification.NotificationPreferencesService;
import com.manning.tutorial.notification.applicationnotificationservice.services.notification.NotificationTemplateService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@RequiredArgsConstructor
public class NotificationService {

    private static final String EMAIL = "EMAIL";
    private static final String SMS = "SMS";
    private final NotificationPreferencesService notificationPreferencesService;
    private final NotificationTemplateService notificationTemplateService;
    private final NotificationGatewayService notificationGatewayService;
    private final NotificationRepository notificationRepository;

    public NotificationResponse sendNotification(NotificationRequest notificationRequest) {
        NotificationPreferencesResponse userNotificationPreferences = notificationPreferencesService.getUserNotificationPreferences(notificationRequest.getCustomerId());
        String notificationMode = getNotificationMode(userNotificationPreferences);
        NotificationTemplateResponse notificationTemplateResponse = notificationTemplateService.getNotificationTemplate(notificationRequest, notificationMode);
        if (notificationMode.equals(EMAIL)) {
            sendEmail(notificationRequest, userNotificationPreferences, notificationMode, notificationTemplateResponse);
        } else {
            sendSms(notificationRequest, userNotificationPreferences, notificationMode, notificationTemplateResponse);
        }
        NotificationEntity entity = notificationRepository.save(getNotificationEntity(notificationRequest, notificationMode));
        return prepareResponse(entity.getId());
    }

    private void sendEmail(NotificationRequest notificationRequest, NotificationPreferencesResponse userNotificationPreferences, String notificationMode, NotificationTemplateResponse notificationTemplateResponse) {
        notificationGatewayService.sendNotificationToGateway(notificationRequest,
                notificationMode,
                notificationTemplateResponse.getEmailContent(),
                userNotificationPreferences.getEmailAddress(),
                notificationTemplateResponse.getEmailSubject(),
                null);
    }

    private void sendSms(NotificationRequest notificationRequest, NotificationPreferencesResponse userNotificationPreferences, String notificationMode, NotificationTemplateResponse notificationTemplateResponse) {
        notificationGatewayService.sendNotificationToGateway(notificationRequest,
                notificationMode,
                notificationTemplateResponse.getSmsContent(),
                null,
                null,
                userNotificationPreferences.getPhoneNumber());
    }

    private NotificationEntity getNotificationEntity(NotificationRequest notificationRequest, String notificationMode) {
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setCustomerId(notificationRequest.getCustomerId());
        notificationEntity.setNotificationMode(notificationMode);
        notificationEntity.setTemplate(notificationRequest.getNotificationTemplateName());
        notificationEntity.setSend(true);
        notificationEntity.setLocalDateTime(LocalDateTime.now());
        return notificationEntity;
    }

    private NotificationResponse prepareResponse(int id) {
        return new NotificationResponse("SUCCESS", "Notification Received Successfully", id);
    }

    private String getNotificationMode(NotificationPreferencesResponse userNotificationPreferences) {
        if (userNotificationPreferences.isEmailPreferenceFlag()) {
            return EMAIL;
        } else {
            return SMS;
        }
    }
}
