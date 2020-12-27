package com.naguib.technicalTasks.SwvlNotificationService.services.impl;


import com.naguib.technicalTasks.SwvlNotificationService.adapter.UseToUserDTOAdapter;
import com.naguib.technicalTasks.SwvlNotificationService.configuration.kafka.producers.NotificationProducerFactory;
import com.naguib.technicalTasks.SwvlNotificationService.dto.*;
import com.naguib.technicalTasks.SwvlNotificationService.entity.GroupNotification;
import com.naguib.technicalTasks.SwvlNotificationService.entity.Receiver;
import com.naguib.technicalTasks.SwvlNotificationService.entity.User;
import com.naguib.technicalTasks.SwvlNotificationService.entity.UserGroup;
import com.naguib.technicalTasks.SwvlNotificationService.repositories.GroupNotificationRepository;
import com.naguib.technicalTasks.SwvlNotificationService.repositories.UserGroupRepository;
import com.naguib.technicalTasks.SwvlNotificationService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.naguib.technicalTasks.SwvlNotificationService.utils.Constants.NotificationReceiverEnum.MULTIPLE;

/*
 * Implementation required for sending grouped NotificationService
 */
@Service("GroupNotificationService")
public class GroupNotificationService extends NotificationServiceImpl {
    private UserGroupRepository userGroupRepository;
    private GroupNotificationRepository groupNotificationRepository;
    private NotificationProducerFactory notificationProducerFactory;
    private UserRepository userRepository;

    @Autowired
    public GroupNotificationService(UserGroupRepository userGroupRepository,
                                    GroupNotificationRepository groupNotificationRepository,
                                    UserRepository userRepository,
                                    NotificationProducerFactory notificationProducerFactory) {
        this.userGroupRepository = userGroupRepository;
        this.groupNotificationRepository = groupNotificationRepository;
        this.notificationProducerFactory = notificationProducerFactory;
        this.userRepository = userRepository;
    }

    @Override
    protected List<Receiver> getAllReceivers(NotificationDTO notificationDTO) {
        List<UserGroup> receivers = userGroupRepository.findByIdIn(notificationDTO.getReceivers());
        return (receivers.size() == notificationDTO.getReceivers().size()) ? (List) receivers : null;
    }

    @Override
    protected ResponseDTO saveNotification(NotificationWithEntitiesDTO notificationDTO) {
        // Replace the vars in the template body by the proper valuer
        String notificationFullBody = getNotificationFullBody(notificationDTO);

        List<GroupNotification> notificationList = getPersonalizedNotificationEntities(notificationDTO);

        saveNotificationToDB(notificationList);

        produceKafkaMessage(notificationList, notificationFullBody);

        // Return success message to client 
        return new ResponseDTO("Notification will be sent in seconds...");
    }

    // Send message to Kafka to be consumed later on
    private void produceKafkaMessage(List<GroupNotification> notificationList, String notificationFullBody) {
        final String body = notificationFullBody;
        final String header = notificationList.get(0).getTemplate().getHeader();
        List<User> users = userRepository
                .findByGroupsIn(notificationList.stream().map(GroupNotification::getUserGroup).collect(Collectors.toList()));
        List<UserDTO> userDTOList = UseToUserDTOAdapter.getUserDTOFromEntity(users);
        MessageDTO message = new MessageDTO();
        message.setId(notificationList.get(0).getId());
        message.setUsers(userDTOList);
        message.setBody(body);
        message.setHeader(header);
        notificationProducerFactory.getNotificationProducer(notificationList.get(0).getType().getType(), MULTIPLE)
                .produce(message);
    }

    // Map NotificationWithEntitiesDTOto PersonalizedNotification Entity
    private List<GroupNotification> getPersonalizedNotificationEntities(NotificationWithEntitiesDTO notificationDTO) {
        List<GroupNotification> notificationList = new ArrayList<>();
        notificationDTO.getReceivers().forEach(receiver -> {
            GroupNotification notification = new GroupNotification();
            notification.setUserGroup((UserGroup) receiver);
            notification.setType(notificationDTO.getNotificationType());
            notification.setTemplate(notificationDTO.getNotificationTemplate());
            notification.setSent(false);
            notificationList.add(notification);
        });
        return notificationList;
    }

    private void saveNotificationToDB(List<GroupNotification> notificationList) {
        groupNotificationRepository.saveAll(notificationList);
    }


}
