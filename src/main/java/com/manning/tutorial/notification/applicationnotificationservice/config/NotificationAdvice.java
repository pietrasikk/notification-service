package com.manning.tutorial.notification.applicationnotificationservice.config;

import com.manning.tutorial.notification.applicationnotificationservice.exceptions.NotificationException;
import com.manning.tutorial.notification.applicationnotificationservice.model.NotificationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
class NotificationAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotificationException.class)
    protected ResponseEntity<NotificationResponse> handleCityNotFoundException(NotificationException ex, WebRequest request) {
        NotificationResponse notificationResponse = new NotificationResponse("ERROR",
                "Something went wrong in module - " + ex.getMessage(),
                0);
        return new ResponseEntity<>(notificationResponse, HttpStatus.OK);
    }
}
