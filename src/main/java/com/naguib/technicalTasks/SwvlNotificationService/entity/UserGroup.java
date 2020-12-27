package com.naguib.technicalTasks.SwvlNotificationService.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_group")
public class UserGroup extends Receiver{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "group_name", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "user_group_user",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    public UserGroup() {
    }
    public UserGroup(long id) {
    setId(id);
    }



    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
