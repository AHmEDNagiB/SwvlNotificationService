package com.naguib.technicalTasks.SwvlNotificationService.services.impl;

import com.naguib.technicalTasks.SwvlNotificationService.dto.NotificationDTO;
import com.naguib.technicalTasks.SwvlNotificationService.dto.NotificationWithEntitiesDTO;
import com.naguib.technicalTasks.SwvlNotificationService.dto.ResponseDTO;
import com.naguib.technicalTasks.SwvlNotificationService.entity.NotificationTemplate;
import com.naguib.technicalTasks.SwvlNotificationService.entity.NotificationType;
import com.naguib.technicalTasks.SwvlNotificationService.entity.Receiver;
import com.naguib.technicalTasks.SwvlNotificationService.repositories.NotificationTemplateRepository;
import com.naguib.technicalTasks.SwvlNotificationService.repositories.NotificationTypeRepository;
import com.naguib.technicalTasks.SwvlNotificationService.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/*
* Template class with Template methods with the basic required steps for sending notification
*/

@Service
public abstract class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationTemplateRepository notificationTemplateRepository;
    @Autowired
    private NotificationTypeRepository notificationTypeRepository;

    @Override
    // Template method, final so subclasses can't override and break the template pattern
    public final ResponseDTO sendNotification(NotificationDTO notificationDTO) {
        NotificationTemplate notificationTemplate = getNotificationTemplate(notificationDTO);
        if (notificationDTO == null) {
            return new ResponseDTO("Wrong Notification Template", 400);
        }
        NotificationType notificationType = getNotificationType(notificationDTO);
        if (notificationType == null) {
            return new ResponseDTO("Wrong Notification type", 400);
        }
        List<Receiver> allReceivers = this.getAllReceivers(notificationDTO);
        if (allReceivers == null) {
            return new ResponseDTO("Some invalid notification receivers ids", 400);
        }

        return saveNotification(new NotificationWithEntitiesDTO(allReceivers
                , notificationTemplate
                , notificationType
                , notificationDTO.getTemplateVars()
        ));
    }

    // Validate that the sent notificationTemplate id is correct id
    protected NotificationTemplate getNotificationTemplate(NotificationDTO notificationDTO) {
        NotificationTemplate template = notificationTemplateRepository
                .findById(notificationDTO.getTemplateId()).orElse(null);
        if (template != null && isValidNotificationTemplateVars(template, notificationDTO)) {
            return template;
        }
        return null;
    }

    // Validate that the number of sent notificationTemplate vars that will used to notificationTemplate is correct
    protected boolean isValidNotificationTemplateVars(NotificationTemplate template, NotificationDTO notificationDTO) {
        return template.getNumberOfVariables() == notificationDTO.getTemplateVars().size();
    }

    // Validate that the sent NotificationType id is correct id
    protected NotificationType getNotificationType(NotificationDTO notificationDTO) {
        NotificationType type = notificationTypeRepository
                .findById(notificationDTO.getTypeId()).orElse(null);
        return type;
    }

    // Replace the vars in the template body by the proper valuer
    protected String getNotificationFullBody(NotificationWithEntitiesDTO notificationDTO) {
        String notificationFullBody = notificationDTO.getNotificationTemplate().getBody();
        for (Map.Entry<String, String> entry : notificationDTO.getTemplateVars().entrySet()) {
            notificationFullBody = notificationFullBody.replaceAll(entry.getKey(), entry.getValue());
        }
        return notificationFullBody;
    }

    // This methods is abstract so that each subclass can get the receivers from the proper table
    protected abstract List<Receiver> getAllReceivers(NotificationDTO notificationDTO);

    // This methods is abstract so that each subclass can save the notification to the right table and kafka topic
    protected abstract ResponseDTO saveNotification(NotificationWithEntitiesDTO notificationDTO);
}
