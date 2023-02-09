package com.ogbc.archive.api;

import com.ogbc.archive.api.dto.RestDto;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Hidden
public class HealthRestService
{
    @GetMapping("/")
    public ResponseEntity<?> handleHealthCheck()
    {
        return new ResponseEntity<RestDto>(new RestDto<String>(null, "UP"), HttpStatus.OK);
    }
}
