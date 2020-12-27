package com.naguib.technicalTasks.SwvlNotificationService.entity;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "personalized_notification")
@PrimaryKeyJoinColumn(name = "id")
public class PersonalizedNotification extends Notification {
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PersonalizedNotification.class.getSimpleName() + "[", "]")

                .add(super.toString())
                .add("user=" + user)
                .toString();
    }
}
