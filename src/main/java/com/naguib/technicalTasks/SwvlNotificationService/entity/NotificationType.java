package com.naguib.technicalTasks.SwvlNotificationService.entity;

import com.naguib.technicalTasks.SwvlNotificationService.utils.Constants;

import javax.persistence.*;

@Entity
@Table(name = "notification_type")
public class NotificationType {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Constants.NotificationTypeEnum type;

    public NotificationType() {
    }

    public NotificationType(long id) {
        this.id = id;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Constants.NotificationTypeEnum getType() {
        return type;
    }

    public void setType(Constants.NotificationTypeEnum type) {
        this.type = type;
    }
}
