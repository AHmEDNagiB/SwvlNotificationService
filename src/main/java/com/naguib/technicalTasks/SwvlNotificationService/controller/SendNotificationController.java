package com.naguib.technicalTasks.SwvlNotificationService.controller;

import com.naguib.technicalTasks.SwvlNotificationService.dto.NotificationDTO;
import com.naguib.technicalTasks.SwvlNotificationService.dto.ResponseDTO;
import com.naguib.technicalTasks.SwvlNotificationService.services.NotificationService;
import com.naguib.technicalTasks.SwvlNotificationService.utils.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/*
 * This controller responsible for exposing 2 API that are used for sending personalized and grouped Notification
 */

@RestController
@RequestMapping(value = "/send-notification")
public class SendNotificationController {
    private UtilService utilService;
    private NotificationService personalizedNotificationService;
    private NotificationService groupNotificationService;

    @Autowired
    public SendNotificationController(UtilService utilService,
                                      @Qualifier("PersonalizedNotificationService") NotificationService personalizedNotificationService,
                                      @Qualifier("GroupNotificationService") NotificationService groupNotificationService) {
        this.utilService = utilService;
        this.personalizedNotificationService = personalizedNotificationService;
        this.groupNotificationService = groupNotificationService;
    }

    @PostMapping("/personalized")
    public ResponseEntity<ResponseDTO> sendPersonalizedNotification(@RequestBody @Valid NotificationDTO requestDTO) {
        ResponseDTO responseDTO = personalizedNotificationService.sendNotification(requestDTO);
        return utilService.getResponse(responseDTO);
    }

    @PostMapping("/grouped")
    public ResponseEntity<ResponseDTO> sendGroupedNotification(@RequestBody @Valid NotificationDTO requestDTO) {
        ResponseDTO responseDTO = groupNotificationService.sendNotification(requestDTO);
        return utilService.getResponse(responseDTO);
    }


}
