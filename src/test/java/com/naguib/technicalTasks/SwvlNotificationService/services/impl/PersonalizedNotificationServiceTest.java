package com.naguib.technicalTasks.SwvlNotificationService.services.impl;

import com.naguib.technicalTasks.SwvlNotificationService.SwvlNotificationServiceApplication;
import com.naguib.technicalTasks.SwvlNotificationService.configuration.kafka.producers.NotificationProducerFactory;
import com.naguib.technicalTasks.SwvlNotificationService.dto.NotificationDTO;
import com.naguib.technicalTasks.SwvlNotificationService.repositories.PersonalizedNotificationRepository;
import com.naguib.technicalTasks.SwvlNotificationService.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SwvlNotificationServiceApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonalizedNotificationServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PersonalizedNotificationRepository personalizedNotificationRepository;
    @Mock
    private NotificationProducerFactory notificationProducerFactory;

    @InjectMocks
    private PersonalizedNotificationService personalizedNotificationService;


    @Test
    public void test_getAllReceivers_userRepositoryReturnNull() {
        when(userRepository.findByIdIn(any())).thenReturn(null);
        assertNull(personalizedNotificationService.getAllReceivers(new NotificationDTO()));
    }
}