package com.manning.tutorial.notification.applicationnotificationservice.services;

import com.manning.tutorial.notification.applicationnotificationservice.entities.NotificationEntity;
import com.manning.tutorial.notification.applicationnotificationservice.entities.NotificationRecordEntity;
import com.manning.tutorial.notification.applicationnotificationservice.model.NotificationRequest;
import com.manning.tutorial.notification.applicationnotificationservice.model.NotificationResponse;
import com.manning.tutorial.notification.applicationnotificationservice.model.notification.preferences.NotificationPreferencesResponse;
import com.manning.tutorial.notification.applicationnotificationservice.model.notification.template.NotificationTemplateResponse;
import com.manning.tutorial.notification.applicationnotificationservice.repositories.NotificationRepository;
import com.manning.tutorial.notification.applicationnotificationservice.services.notification.NotificationGatewayService;
import com.manning.tutorial.notification.applicationnotificationservice.services.notification.NotificationPreferencesService;
import com.manning.tutorial.notification.applicationnotificationservice.services.notification.NotificationTemplateService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class NotificationService {

    private static final String EMAIL = "EMAIL";
    private static final String SMS = "SMS";
    private final NotificationPreferencesService notificationPreferencesService;
    private final NotificationTemplateService notificationTemplateService;
    private final NotificationGatewayService notificationGatewayService;
    private final NotificationRepository notificationRepository;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @CircuitBreaker(name= "sendNotification", fallbackMethod = "fallbackMethod")
    @Bulkhead(name = "bulkheadSendNotification", fallbackMethod = "fallbackMethod")
    @Retry(name = "retrySendNotification", fallbackMethod = "fallbackMethod")
    @RateLimiter(name = "rateLimiterSendNotification", fallbackMethod = "fallbackMethod")
    public NotificationResponse sendNotification(NotificationRequest notificationRequest) {
        logger.info("In the Notification Service API Class");
        NotificationPreferencesResponse userNotificationPreferences = notificationPreferencesService.getUserNotificationPreferences(notificationRequest.getCustomerId());
        String notificationMode = getNotificationMode(userNotificationPreferences);
        NotificationTemplateResponse notificationTemplateResponse = notificationTemplateService.getNotificationTemplate(notificationRequest, notificationMode);
        if (notificationMode.equals(EMAIL)) {
            sendEmail(notificationRequest, userNotificationPreferences, notificationMode, notificationTemplateResponse);
        } else {
            sendSms(notificationRequest, userNotificationPreferences, notificationMode, notificationTemplateResponse);
        }
        NotificationEntity entity = notificationRepository.save(getNotificationEntity(notificationRequest, notificationMode));
        return prepareResponse(entity.getNotificationRecords().get(0).getId());
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
        NotificationRecordEntity notificationRecordEntity = getNotificationRecordEntity(notificationRequest, notificationMode);
        return getNotificationEntity(notificationRequest, notificationRecordEntity);
    }

    private NotificationEntity getNotificationEntity(NotificationRequest notificationRequest, NotificationRecordEntity notificationRecordEntity) {
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setCustomerId(notificationRequest.getCustomerId());
        if (notificationEntity.getNotificationRecords() == null || notificationEntity.getNotificationRecords().isEmpty()) {
            List<NotificationRecordEntity> list = new ArrayList<>();
            list.add(notificationRecordEntity);
            notificationEntity.setNotificationRecords(list);
        } else {
            List<NotificationRecordEntity> notificationRecords = notificationEntity.getNotificationRecords();
            notificationRecords.add(notificationRecordEntity);
            notificationEntity.setNotificationRecords(notificationRecords);
        }
        return notificationEntity;
    }

    private NotificationRecordEntity getNotificationRecordEntity(NotificationRequest notificationRequest, String notificationMode) {
        NotificationRecordEntity notificationRecordEntity = new NotificationRecordEntity();
        notificationRecordEntity.setCustomerId(notificationRequest.getCustomerId());
        notificationRecordEntity.setNotificationMode(notificationMode);
        notificationRecordEntity.setTemplate(notificationRequest.getNotificationTemplateName());
        notificationRecordEntity.setSend(true);
        notificationRecordEntity.setLocalDateTime(LocalDateTime.now());
        return notificationRecordEntity;
    }

    private NotificationResponse prepareResponse(int id) {
        return new NotificationResponse("SUCCESS", "Notification Received Successfully", id);
    }

    private NotificationResponse fallbackMethod(Throwable throwable){
        return new NotificationResponse("ERROR","Something went wrong", 0);
    }

    private String getNotificationMode(NotificationPreferencesResponse userNotificationPreferences) {
        if (userNotificationPreferences.isEmailPreferenceFlag()) {
            return EMAIL;
        } else {
            return SMS;
        }
    }
}
