package com.naguib.technicalTasks.SwvlNotificationService.configuration.kafka.consumers;

import com.naguib.technicalTasks.SwvlNotificationService.dto.MessageDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${kafka.bootstrap.server}")
    private String bootstrapAddress;
    @Value(value = "${kafka.topic.sms.consumers.count}")
    private int consumerCount;
    @Value(value = "${kafka.topic.sms.max.records}")
    private int maxRecords;


    private DefaultKafkaConsumerFactory getDefaultKafkaConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 101);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxRecords/consumerCount);
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 30050);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 60001);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        JsonDeserializer deserializer = new JsonDeserializer();
        deserializer.addTrustedPackages("*");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(
                props,  // your consumer config
                new StringDeserializer(),
                deserializer // Using our newly created deserializer
        );
    }

    @Bean
    public ConsumerFactory<String, MessageDTO> messageConsumerFactory() {
        return getDefaultKafkaConsumerFactory();
    }

    // ContainerFactory for consuming SMS-Notification
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,
            MessageDTO> messageKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MessageDTO> containerFactory =
                new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(messageConsumerFactory());
        containerFactory.setContainerCustomizer(container -> {
            // This very important for pulling messages evey 30 seconds
            container.getContainerProperties().setIdleBetweenPolls(30000l);
        });
        return containerFactory;
    }



}
