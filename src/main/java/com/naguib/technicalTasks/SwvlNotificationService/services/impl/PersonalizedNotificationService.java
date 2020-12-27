package com.naguib.technicalTasks.SwvlNotificationService.services.impl;


import com.naguib.technicalTasks.SwvlNotificationService.adapter.UseToUserDTOAdapter;
import com.naguib.technicalTasks.SwvlNotificationService.configuration.kafka.producers.NotificationProducerFactory;
import com.naguib.technicalTasks.SwvlNotificationService.dto.*;
import com.naguib.technicalTasks.SwvlNotificationService.entity.PersonalizedNotification;
import com.naguib.technicalTasks.SwvlNotificationService.entity.Receiver;
import com.naguib.technicalTasks.SwvlNotificationService.entity.User;
import com.naguib.technicalTasks.SwvlNotificationService.repositories.PersonalizedNotificationRepository;
import com.naguib.technicalTasks.SwvlNotificationService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.naguib.technicalTasks.SwvlNotificationService.utils.Constants.NotificationReceiverEnum.SINGE;


/*
 * Implementation required for sending personalized NotificationService
 */
@Service("PersonalizedNotificationService")
public class PersonalizedNotificationService extends NotificationServiceImpl {
    private UserRepository userRepository;
    private PersonalizedNotificationRepository personalizedNotificationRepository;
    private NotificationProducerFactory notificationProducerFactory;

    @Autowired
    public PersonalizedNotificationService(UserRepository userRepository
            , PersonalizedNotificationRepository personalizedNotificationRepository
            , NotificationProducerFactory notificationProducerFactory) {
        this.userRepository = userRepository;
        this.personalizedNotificationRepository = personalizedNotificationRepository;
        this.notificationProducerFactory = notificationProducerFactory;
    }

    @Override
    protected List<Receiver> getAllReceivers(NotificationDTO notificationDTO) {
        List<User> receivers = userRepository.findByIdIn(notificationDTO.getReceivers());
        return (receivers != null && receivers.size() == notificationDTO.getReceivers().size()) ? (List) receivers : null;
    }

    @Override
    protected ResponseDTO saveNotification(NotificationWithEntitiesDTO notificationDTO) {
        // Replace the vars in the template body by the proper valuer
        String notificationBody = getNotificationFullBody(notificationDTO);

        List<PersonalizedNotification> notificationList = getPersonalizedNotificationEntities(notificationDTO);

        saveNotificationToDB(notificationList);

        produceKafkaMessage(notificationList, notificationBody);

        // Return success message to client
        return new ResponseDTO("Notification will be sent in seconds...");
    }

    // Send message to Kafka to be consumed later on if sms
    private void produceKafkaMessage(List<PersonalizedNotification> notificationList, String notificationBody) {
        notificationList.stream().forEach(notification -> {
            List<UserDTO> userNames = new ArrayList<>();
            MessageDTO message = new MessageDTO();
            userNames.add(UseToUserDTOAdapter.getUserDTOFromEntity(notification.getUser()));
            message.setUsers(userNames);
            message.setId(notification.getId());
            message.setHeader(notification.getTemplate().getHeader());
            message.setBody(this.getNotificationFullBody(notificationBody, notification.getUser()));
            notificationProducerFactory.getNotificationProducer(notification.getType().getType(), SINGE)
                    .produce(message);
        });
    }

    // Map NotificationWithEntitiesDTOto PersonalizedNotification Entity
    private List<PersonalizedNotification> getPersonalizedNotificationEntities(NotificationWithEntitiesDTO notificationDTO) {
        List<PersonalizedNotification> notificationList = new ArrayList<>();
        notificationDTO.getReceivers().forEach(receiver -> {
            PersonalizedNotification notification = new PersonalizedNotification();
            notification.setUser((User) receiver);
            notification.setType(notificationDTO.getNotificationType());
            notification.setTemplate(notificationDTO.getNotificationTemplate());
            notification.setSent(true);
            notificationList.add(notification);
        });
        return notificationList;
    }

    private void saveNotificationToDB(List<PersonalizedNotification> notificationList) {
        personalizedNotificationRepository.saveAll(notificationList);
    }

    // custom message for each usr
    protected String getNotificationFullBody(String notificationFullBody, User user) {
        notificationFullBody = notificationFullBody.replaceAll("user-name", user.getName());
        return notificationFullBody;
    }

}
