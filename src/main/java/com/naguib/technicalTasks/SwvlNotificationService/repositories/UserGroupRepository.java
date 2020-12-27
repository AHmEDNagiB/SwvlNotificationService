package com.naguib.technicalTasks.SwvlNotificationService.repositories;

import com.naguib.technicalTasks.SwvlNotificationService.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    List<UserGroup> findByIdIn(List<Long> ids);
}
