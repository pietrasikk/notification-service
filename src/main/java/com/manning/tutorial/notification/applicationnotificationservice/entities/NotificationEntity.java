package com.manning.tutorial.notification.applicationnotificationservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "notification")
public class NotificationEntity {

    @Id
    @Column(name = "customer_id", nullable = false)
    private String customerId;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "customerId")
    private List<NotificationRecordEntity> notificationRecords;
}
