package com.naguib.technicalTasks.SwvlNotificationService.dto;

import com.naguib.technicalTasks.SwvlNotificationService.entity.NotificationTemplate;
import com.naguib.technicalTasks.SwvlNotificationService.entity.NotificationType;
import com.naguib.technicalTasks.SwvlNotificationService.entity.Receiver;

import java.util.List;
import java.util.Map;

// Same as NotificationDTO but with entities of each id
public class NotificationWithEntitiesDTO {
    private List<Receiver> receivers;

    private NotificationTemplate notificationTemplate;

    private NotificationType notificationType;

    private Map<String, String> templateVars;

    public NotificationWithEntitiesDTO() {
    }

    public NotificationWithEntitiesDTO(List<Receiver> receivers
            , NotificationTemplate notificationTemplate
            , NotificationType notificationType
            , Map<String, String> templateVars) {
        this.receivers = receivers;
        this.notificationTemplate = notificationTemplate;
        this.notificationType = notificationType;
        this.templateVars = templateVars;
    }

    public List<Receiver> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<Receiver> receivers) {
        this.receivers = receivers;
    }

    public NotificationTemplate getNotificationTemplate() {
        return notificationTemplate;
    }

    public void setNotificationTemplate(NotificationTemplate notificationTemplate) {
        this.notificationTemplate = notificationTemplate;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public Map<String, String> getTemplateVars() {
        return templateVars;
    }

    public void setTemplateVars(Map<String, String> templateVars) {
        this.templateVars = templateVars;
    }
}
