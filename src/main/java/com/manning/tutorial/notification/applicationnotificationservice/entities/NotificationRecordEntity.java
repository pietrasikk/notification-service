package com.manning.tutorial.notification.applicationnotificationservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "notification_record")
public class NotificationRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "customer_id", nullable = false)
    private String customerId;
    @Column(name = "mode", nullable = false)
    private String notificationMode;
    @Column(name = "template", nullable = false)
    private String template;
    @Column(name = "sent", nullable = false)
    private boolean isSend;
    @Column(name = "date", nullable = false)
    private LocalDateTime localDateTime;
}
