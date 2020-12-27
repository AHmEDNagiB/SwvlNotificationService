package com.naguib.technicalTasks.SwvlNotificationService.controller;

import com.google.gson.Gson;
import com.naguib.technicalTasks.SwvlNotificationService.SwvlNotificationServiceApplication;
import com.naguib.technicalTasks.SwvlNotificationService.dto.NotificationDTO;
import com.naguib.technicalTasks.SwvlNotificationService.dto.ResponseDTO;
import com.naguib.technicalTasks.SwvlNotificationService.services.NotificationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SwvlNotificationServiceApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SendNotificationControllerTest {

    @Mock
    @Qualifier("PersonalizedNotificationService")
    private NotificationService personalizedNotificationService;
    @Mock
    @Qualifier("GroupNotificationService")
    private NotificationService groupNotificationService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    Gson gson = new Gson();

    @InjectMocks
    @Autowired
    private SendNotificationController controller;

    NotificationDTO requestDTO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        requestDTO = new NotificationDTO();
    }

    @Test
    public void test_sendPersonalizedNotification_noReceivers() throws Exception {
        requestDTO.setTemplateId(1);
        requestDTO.setTypeId(2);
        requestDTO.setReceivers(null);
        mockMvc.perform(post("/send-notification/personalized")
                .contentType(APPLICATION_JSON).content(gson.toJson(requestDTO)))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void test_sendPersonalizedNotification_zeroTemplateId() throws Exception {
        requestDTO.setTemplateId(0);
        requestDTO.setTypeId(2);
        requestDTO.setReceivers(new ArrayList<>());
        mockMvc.perform(post("/send-notification/personalized")
                .contentType(APPLICATION_JSON).content(gson.toJson(requestDTO)))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void test_sendPersonalizedNotification_validRequest() throws Exception {
        when(personalizedNotificationService.sendNotification(any())).thenReturn(new ResponseDTO("valid response"));
        requestDTO.setTemplateId(1);
        requestDTO.setTypeId(2);
        requestDTO.setReceivers(new ArrayList<>());
        mockMvc.perform(post("/send-notification/personalized")
                .contentType(APPLICATION_JSON).content(gson.toJson(requestDTO)))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void test_sendGroupedNotification_zeroTemplateId() throws Exception {
        requestDTO.setTemplateId(0);
        requestDTO.setTypeId(2);
        requestDTO.setReceivers(new ArrayList<>());
        mockMvc.perform(post("/send-notification/grouped")
                .contentType(APPLICATION_JSON).content(gson.toJson(requestDTO)))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void test_sendGroupedNotification_validRequest() throws Exception {
        when(groupNotificationService.sendNotification(any())).thenReturn(new ResponseDTO("valid response"));
        requestDTO.setTemplateId(1);
        requestDTO.setTypeId(2);
        requestDTO.setReceivers(new ArrayList<>());
        mockMvc.perform(post("/send-notification/grouped")
                .contentType(APPLICATION_JSON).content(gson.toJson(requestDTO)))
                .andExpect(status().isOk()).andReturn();
    }
}