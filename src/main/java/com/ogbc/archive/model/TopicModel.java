package com.ogbc.archive.model;

import com.ogbc.archive.data.entity.ContentEntity;
import com.ogbc.archive.data.entity.TopicEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @Size(max = 128)
    String name;

    private List<ContentModel> content;

    public TopicModel(TopicEntity entity)
    {
        id = entity.getId();
        name = entity.getName();
    }
}
