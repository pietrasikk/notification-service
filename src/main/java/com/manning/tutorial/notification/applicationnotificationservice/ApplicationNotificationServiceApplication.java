package com.manning.tutorial.notification.applicationnotificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationNotificationServiceApplication.class, args);
	}

}
