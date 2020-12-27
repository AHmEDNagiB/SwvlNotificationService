package com.naguib.technicalTasks.SwvlNotificationService.services.impl;

import com.naguib.technicalTasks.SwvlNotificationService.dto.MessageDTO;
import com.naguib.technicalTasks.SwvlNotificationService.services.MessageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/*
 * This class should contain the implementation required for sending email if ever needed
 */

@Service("EmailMessageProvider")
public class EmailMessageProvider implements MessageProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailMessageProvider.class);


    @Override
    public void send(MessageDTO message) {
        // this should be implemented
        LOGGER.info(String.format("Sending %s by SMS", message));
    }
}
