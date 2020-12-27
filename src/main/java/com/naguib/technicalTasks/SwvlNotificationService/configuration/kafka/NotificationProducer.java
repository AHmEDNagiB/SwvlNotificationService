package com.naguib.technicalTasks.SwvlNotificationService.configuration.kafka;

import com.naguib.technicalTasks.SwvlNotificationService.dto.MessageDTO;

// This interface for Factory Pattern
public interface NotificationProducer {
    void produce(MessageDTO message);
}
