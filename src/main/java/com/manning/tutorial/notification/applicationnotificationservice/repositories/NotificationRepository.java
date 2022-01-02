package com.manning.tutorial.notification.applicationnotificationservice.repositories;

import com.manning.tutorial.notification.applicationnotificationservice.entities.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {
}
