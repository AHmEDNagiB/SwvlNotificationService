package com.naguib.technicalTasks.SwvlNotificationService.repositories;

import com.naguib.technicalTasks.SwvlNotificationService.entity.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationTypeRepository extends JpaRepository<NotificationType, Long>{

}
