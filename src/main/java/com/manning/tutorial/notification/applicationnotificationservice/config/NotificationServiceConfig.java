package com.manning.tutorial.notification.applicationnotificationservice.config;

import com.manning.tutorial.notification.applicationnotificationservice.formatters.NotificationGatewayFormatter;
import com.manning.tutorial.notification.applicationnotificationservice.formatters.NotificationPreferencesFormatter;
import com.manning.tutorial.notification.applicationnotificationservice.formatters.NotificationTemplateFormatter;
import com.manning.tutorial.notification.applicationnotificationservice.integration.NotificationGatewayIntegration;
import com.manning.tutorial.notification.applicationnotificationservice.integration.NotificationPreferencesIntegration;
import com.manning.tutorial.notification.applicationnotificationservice.integration.NotificationTemplateIntegration;
import com.manning.tutorial.notification.applicationnotificationservice.repositories.NotificationRepository;
import com.manning.tutorial.notification.applicationnotificationservice.services.NotificationService;
import com.manning.tutorial.notification.applicationnotificationservice.services.notification.NotificationGatewayService;
import com.manning.tutorial.notification.applicationnotificationservice.services.notification.NotificationPreferencesService;
import com.manning.tutorial.notification.applicationnotificationservice.services.notification.NotificationTemplateService;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class NotificationServiceConfig {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    NotificationPreferencesFormatter notificationPreferencesFormatter() {
        return new NotificationPreferencesFormatter();
    }

    @Bean
    NotificationTemplateFormatter notificationTemplateFormatter() {
        return new NotificationTemplateFormatter();
    }

    @Bean
    NotificationGatewayFormatter notificationGatewayFormatter() {
        return new NotificationGatewayFormatter();
    }

    @Bean
    NotificationPreferencesIntegration notificationPreferencesIntegration(RestTemplate restTemplate) {
        return new NotificationPreferencesIntegration(restTemplate);
    }

    @Bean
    NotificationTemplateIntegration notificationTemplateIntegration(RestTemplate restTemplate) {
        return new NotificationTemplateIntegration(restTemplate);
    }

    @Bean
    NotificationGatewayIntegration notificationGatewayIntegration(RestTemplate restTemplate) {
        return new NotificationGatewayIntegration(restTemplate);
    }

    @Bean
    NotificationPreferencesService notificationPreferencesService(NotificationPreferencesIntegration notificationPreferencesIntegration, NotificationPreferencesFormatter notificationPreferencesFormatter) {
        return new NotificationPreferencesService(notificationPreferencesIntegration, notificationPreferencesFormatter);
    }

    @Bean
    NotificationTemplateService notificationTemplateService(NotificationTemplateIntegration notificationTemplateIntegration, NotificationTemplateFormatter notificationTemplateFormatter) {
        return new NotificationTemplateService(notificationTemplateIntegration, notificationTemplateFormatter);
    }

    @Bean
    NotificationGatewayService notificationGatewayService(NotificationGatewayIntegration notificationGatewayIntegration, NotificationGatewayFormatter notificationGatewayFormatter) {
        return new NotificationGatewayService(notificationGatewayIntegration, notificationGatewayFormatter);
    }

    @Bean
    NotificationService notificationService(NotificationPreferencesService notificationPreferencesService,
                                            NotificationTemplateService notificationTemplateService,
                                            NotificationGatewayService notificationGatewayService,
                                            NotificationRepository notificationRepository) {
        return new NotificationService(notificationPreferencesService, notificationTemplateService, notificationGatewayService, notificationRepository);
    }
}
