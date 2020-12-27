package com.naguib.technicalTasks.SwvlNotificationService.configuration.kafka.Topics;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${kafka.bootstrap.server}")
    private String bootstrapAddress;

    @Value(value = "${kafka.topic.ppn}")
    private String personalizedPushNotification;

    @Value(value = "${kafka.topic.gpn}")
    private String groupedPushNotification;

    @Value(value = "${kafka.topic.sms}")
    private String smsPushNotification;


    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic smsNotification() {
        return TopicBuilder.name(smsPushNotification).build();
    }

    @Bean
    public NewTopic personalizedPushedNotification() {
        return TopicBuilder.name(personalizedPushNotification).partitions(2).build();
    }

    @Bean
    public NewTopic groupedPushedNotification() {
        return TopicBuilder.name(groupedPushNotification).partitions(2).build();
    }

}