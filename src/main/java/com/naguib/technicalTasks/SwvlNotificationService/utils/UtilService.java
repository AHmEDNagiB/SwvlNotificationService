package com.naguib.technicalTasks.SwvlNotificationService.utils;

import com.naguib.technicalTasks.SwvlNotificationService.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UtilService {

    public ResponseEntity<ResponseDTO> getResponse(ResponseDTO responseDTO) {
        if (responseDTO != null && responseDTO.getErrorMessage() == null) {
            return new ResponseEntity(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity(responseDTO, HttpStatus.valueOf(responseDTO.getErrorCode()));
        }
    }

}
