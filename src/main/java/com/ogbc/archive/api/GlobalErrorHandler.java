package com.ogbc.archive.api;

import com.ogbc.archive.api.dto.RestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler
{
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleError()
    {
        return new ResponseEntity<>(new RestDto(null, "Internal Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
