package com.naguib.technicalTasks.SwvlNotificationService.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationDTO {
    @NotNull
    List<Long> receivers;

    @Min(1)
    long templateId;

    @Min(1)
    long typeId;

    Map<String, String> templateVars = new HashMap<>();


    public List<Long> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<Long> receivers) {
        this.receivers = receivers;
    }

    public long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(long templateId) {
        this.templateId = templateId;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public Map<String, String> getTemplateVars() {
        return templateVars;
    }

    public void setTemplateVars(Map<String, String> templateVars) {
        this.templateVars = templateVars;
    }
}
