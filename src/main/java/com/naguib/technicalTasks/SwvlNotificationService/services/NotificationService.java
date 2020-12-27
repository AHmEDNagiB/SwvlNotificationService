package com.naguib.technicalTasks.SwvlNotificationService.services;

import com.naguib.technicalTasks.SwvlNotificationService.dto.NotificationDTO;
import com.naguib.technicalTasks.SwvlNotificationService.dto.ResponseDTO;

public interface NotificationService {
    ResponseDTO sendNotification(NotificationDTO dto);
}
