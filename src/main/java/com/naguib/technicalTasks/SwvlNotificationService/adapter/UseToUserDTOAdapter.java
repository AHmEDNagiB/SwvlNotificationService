package com.naguib.technicalTasks.SwvlNotificationService.adapter;

import com.naguib.technicalTasks.SwvlNotificationService.dto.UserDTO;
import com.naguib.technicalTasks.SwvlNotificationService.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UseToUserDTOAdapter {
    public static UserDTO getUserDTOFromEntity(User user) {
        if (user == null)
            return null;
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setUserName(user.getUserName());
        userDTO.setName(user.getName());
        userDTO.setPhoneNumber(user.getName());
        return userDTO;
    }

    public static List<UserDTO> getUserDTOFromEntity(List<User> users) {
        if (users == null)
            return null;
        List<UserDTO> userDTOList = new ArrayList<>();
        users.forEach(user -> {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setUserName(user.getUserName());
        userDTO.setName(user.getName());
        userDTO.setPhoneNumber(user.getName());
        userDTOList.add(userDTO);
        });
        return userDTOList;
    }
}
