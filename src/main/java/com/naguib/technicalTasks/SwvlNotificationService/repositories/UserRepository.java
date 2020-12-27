package com.naguib.technicalTasks.SwvlNotificationService.repositories;

import com.naguib.technicalTasks.SwvlNotificationService.entity.User;
import com.naguib.technicalTasks.SwvlNotificationService.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List <User> findByIdIn(List<Long> ids);
    List <User> findByGroupsIn(List<UserGroup> groups);
}
