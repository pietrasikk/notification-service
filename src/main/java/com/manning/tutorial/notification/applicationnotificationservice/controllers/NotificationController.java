package com.manning.tutorial.notification.applicationnotificationservice.controllers;

import com.manning.tutorial.notification.applicationnotificationservice.model.NotificationRequest;
import com.manning.tutorial.notification.applicationnotificationservice.model.NotificationResponse;
import com.manning.tutorial.notification.applicationnotificationservice.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class NotificationController {

    private final NotificationService notificationService;

    @PostMapping(path = "/api/notifications",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public NotificationResponse sendNotification(@RequestBody NotificationRequest notificationRequest) {
        return notificationService.sendNotification(notificationRequest);
    }

    @GetMapping("/api/notifications/healthcheck")
    public String healthCheck (){
        return "UP";
    }
}
