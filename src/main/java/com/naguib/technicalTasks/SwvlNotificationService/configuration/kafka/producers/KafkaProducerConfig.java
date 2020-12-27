package com.naguib.technicalTasks.SwvlNotificationService.configuration.kafka.producers;

import com.naguib.technicalTasks.SwvlNotificationService.dto.MessageDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.bootstrap.server}")
    private String bootstrapAddress;

    @Bean(name = "NotificationProducerFactory")
    public ProducerFactory<String, MessageDTO> notificationProducerFactory() {
        return new DefaultKafkaProducerFactory<>(getConfigPropsMap());
    }

    @Bean(name = "NotificationKafkaTemplate")
    public KafkaTemplate<String, MessageDTO> pushNotificationTemplate() {
        return new KafkaTemplate<>(notificationProducerFactory());
    }



    private Map<String, Object> getConfigPropsMap() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        return configProps;
    }
}
