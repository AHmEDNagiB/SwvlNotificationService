package com.naguib.technicalTasks.SwvlNotificationService.services.impl;

import com.naguib.technicalTasks.SwvlNotificationService.dto.MessageDTO;
import com.naguib.technicalTasks.SwvlNotificationService.services.MessageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/*
 * This class should contain the implementation required for sending SMS
 */

@Service("SMSMessageProvider")
public class SMSMessageProvider implements MessageProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(SMSMessageProvider.class);


    @Override
    public void send(MessageDTO message) {
        LOGGER.info(String.format("Sending %s by SMS", message));
    }
}
