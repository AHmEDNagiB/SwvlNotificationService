package com.naguib.technicalTasks.SwvlNotificationService.dto;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

public class MessageDTO implements Serializable {
    private long id;
    private List<UserDTO> users;
    private String body;
    private String header;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MessageDTO.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("users='" + users + "'")
                .add("body='" + body + "'")
                .add("header='" + header + "'")
                .toString();
    }
}
