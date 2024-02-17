package com.ogbc.archive.data.entity;

import com.ogbc.archive.api.model.TopicModel;
import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Entity(name="TOPIC")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class TopicEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @ManyToMany(mappedBy = "topics")
    private List<ContentEntity> content;

    public TopicEntity(TopicModel model)
    {
        name = model.getName();
    }
}
