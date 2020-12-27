package com.naguib.technicalTasks.SwvlNotificationService.configuration.kafka.consumers;

import com.naguib.technicalTasks.SwvlNotificationService.dto.MessageDTO;
import com.naguib.technicalTasks.SwvlNotificationService.services.MessageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {
    private MessageProvider smsMessageProvider;

    @Value(value = "${kafka.topic.sms}")
    private String smsPushNotification;

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationConsumer.class);

    @Autowired
    public NotificationConsumer(@Qualifier("SMSMessageProvider") MessageProvider smsMessageProvider) {
        this.smsMessageProvider = smsMessageProvider;
    }

    @KafkaListener(
            topics = "SMS_NOTIFICATION",
            groupId = "SMS_NOTIFICATION",
            containerFactory = "messageKafkaListenerContainerFactory")
    void smsNotificationListener(MessageDTO message) {
        LOGGER.info(String.format("smsNotificationListener receives message with id %s", message.getId()));
        smsMessageProvider.send(message);
    }

    // Two Listener for simulating that there are two instance of consumer
    @KafkaListener(
            topics = "SMS_NOTIFICATION",
            groupId = "SMS_NOTIFICATION",
            containerFactory = "messageKafkaListenerContainerFactory")
    void smsNotificationListener2(MessageDTO message) {
        LOGGER.info(String.format("smsNotificationListener2 receives message with id %s", message.getId()));
        smsMessageProvider.send(message);
    }


}
