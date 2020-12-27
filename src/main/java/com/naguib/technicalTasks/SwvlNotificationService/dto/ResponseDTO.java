package com.naguib.technicalTasks.SwvlNotificationService.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

// Generic Response DTO for all APIs
@JsonInclude(Include.NON_NULL)
public class ResponseDTO<T> {

    private T data;
    private String errorMessage;
    private int errorCode;


    public ResponseDTO(T data) {
        this.data = data;
    }


    public ResponseDTO(String errorMessage, int errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }


}
