package com.ogbc.archive.api;

import com.ogbc.archive.api.dto.RestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
    public ResponseEntity handleConstraintViolationException()
    {
        return new ResponseEntity<>(new RestDto(null, "Invalid path variable(s), request parameter(s), or request body provided"), HttpStatus.BAD_REQUEST);
    }
}
