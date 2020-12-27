package com.naguib.technicalTasks.SwvlNotificationService.configuration.kafka.producers;

import com.naguib.technicalTasks.SwvlNotificationService.configuration.kafka.NotificationProducer;
import com.naguib.technicalTasks.SwvlNotificationService.utils.Constants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducerFactory {

    private NotificationProducer personalizedPushNotificationProducer;
    private NotificationProducer groupedPushNotificationProducer;
    private NotificationProducer smsProducer;

    public NotificationProducerFactory(@Qualifier("PersonalizedPushNotificationProducer") NotificationProducer personalizedPushNotificationProducer
            , @Qualifier("GroupedPushNotificationProducer") NotificationProducer groupedPushNotificationProducer
            , @Qualifier("SMSNotificationProducer") NotificationProducer smsProducer) {
        this.personalizedPushNotificationProducer = personalizedPushNotificationProducer;
        this.groupedPushNotificationProducer = groupedPushNotificationProducer;
        this.smsProducer = smsProducer;

    }

    public NotificationProducer getNotificationProducer(Constants.NotificationTypeEnum notificationType, Constants.NotificationReceiverEnum receiverEnum) {
        switch (notificationType) {
            case PUSH_NOTIFICATION:
            default:
                switch (receiverEnum) {
                    case SINGE:
                    default:
                        return this.personalizedPushNotificationProducer;
                    case MULTIPLE:
                        return this.groupedPushNotificationProducer;
                }
            case SMS_NOTIFICATION:
                return this.smsProducer;
        }

    }
}
