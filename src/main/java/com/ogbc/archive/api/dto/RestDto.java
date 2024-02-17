package com.ogbc.archive.api.dto;

import com.ogbc.archive.api.model.ContentModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

/**
 * This class is the vehicle of HTTP REST communication
 * @param <T> The object model type returned with the response
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestDto<T>
{
    @Schema(description = "Response data", implementation = ContentModel.class)
    public List<T> data;
    @Schema(description = "Response message")
    public String message;
}
