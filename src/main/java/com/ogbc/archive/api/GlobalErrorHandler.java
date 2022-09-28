package com.ogbc.archive.api;

import com.ogbc.archive.api.dto.RestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

/**
 * This class handles all cross-cutting errors
 */
@ControllerAdvice
public class GlobalErrorHandler
{
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException()
    {
        return new ResponseEntity<>(new RestDto(null, "Internal Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleConstraintViolationException()
    {
        return new ResponseEntity<>(new RestDto(null, "Invalid path variable(s) or request parameter(s) provided"), HttpStatus.BAD_REQUEST);
    }
}
