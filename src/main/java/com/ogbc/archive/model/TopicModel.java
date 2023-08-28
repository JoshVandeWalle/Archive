package com.ogbc.archive.model;

import com.ogbc.archive.data.entity.TopicEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TopicModel
{
    @Schema(description = "Topic ID number", example = "37")
    Long id;

    @NotBlank
    @NonNull
    @Size(max = 128)
    @Schema(description = "The topic covered", example = "God's Love")
    String name;

    private List<ContentModel> content;

    public TopicModel(TopicEntity entity)
    {
        id = entity.getId();
        name = entity.getName();
    }
}
