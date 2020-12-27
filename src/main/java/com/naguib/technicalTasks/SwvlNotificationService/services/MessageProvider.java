package com.naguib.technicalTasks.SwvlNotificationService.services;

import com.naguib.technicalTasks.SwvlNotificationService.dto.MessageDTO;

public interface MessageProvider {
    void send(MessageDTO message);
}
