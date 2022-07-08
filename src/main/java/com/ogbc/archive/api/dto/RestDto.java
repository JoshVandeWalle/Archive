package com.ogbc.archive.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestDto<T>
{
    public List<T> data;
    public String message;
}
