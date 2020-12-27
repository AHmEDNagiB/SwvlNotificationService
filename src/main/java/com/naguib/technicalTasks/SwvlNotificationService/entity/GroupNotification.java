package com.naguib.technicalTasks.SwvlNotificationService.entity;

import javax.persistence.*;

@Entity
@Table(name = "group_notification")
@PrimaryKeyJoinColumn(name = "id")
public class GroupNotification extends Notification {
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    UserGroup userGroup;

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }
}
