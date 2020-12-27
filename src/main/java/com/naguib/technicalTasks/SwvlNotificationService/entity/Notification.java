package com.naguib.technicalTasks.SwvlNotificationService.entity;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "notification")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Notification {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "temp_id", referencedColumnName = "id")
    private NotificationTemplate template;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private NotificationType type;

    @Column(name = "is_sent", nullable = false , columnDefinition = "boolean default false")
    private boolean isSent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public NotificationTemplate getTemplate() {
        return template;
    }

    public void setTemplate(NotificationTemplate template) {
        this.template = template;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Notification.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("template=" + template.getBody())
                .add("type=" + type.getType())
                .add("isSent=" + isSent)
                .toString();
    }
}
