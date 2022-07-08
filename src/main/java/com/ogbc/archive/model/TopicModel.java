package com.ogbc.archive.model;

import com.ogbc.archive.data.entity.ContentEntity;
import com.ogbc.archive.data.entity.TopicEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class TopicModel
{
    Long id;

    @NotBlank
    @NonNull
    String name;

    private List<ContentModel> content;

    public TopicModel(TopicEntity entity)
    {
        id = entity.getId();
        name = entity.getName();
    }
}
