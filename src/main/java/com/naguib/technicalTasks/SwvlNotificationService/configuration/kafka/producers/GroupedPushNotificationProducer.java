package com.naguib.technicalTasks.SwvlNotificationService.configuration.kafka.producers;

import com.naguib.technicalTasks.SwvlNotificationService.configuration.kafka.NotificationProducer;
import com.naguib.technicalTasks.SwvlNotificationService.dto.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service("GroupedPushNotificationProducer")
public class GroupedPushNotificationProducer implements NotificationProducer {

    @Autowired
    private KafkaTemplate<String, MessageDTO> kafkaTemplate;

    @Value(value = "${kafka.topic.gpn}")
    private String groupedPushNotification;

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupedPushNotificationProducer.class);


    @Override
    public void produce(MessageDTO message) {
        LOGGER.info(String.format("producing => $1%s in => $2%s", message, groupedPushNotification));
        kafkaTemplate.send(groupedPushNotification, message);
    }
}
