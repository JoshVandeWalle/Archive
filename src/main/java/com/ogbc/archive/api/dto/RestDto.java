package com.ogbc.archive.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This class is the vehicle of HTTP REST communication
 * @param <T> The object model type returned with the response
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestDto<T>
{
    public List<T> data;
    public String message;
}
