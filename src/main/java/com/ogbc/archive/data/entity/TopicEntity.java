package com.ogbc.archive.data.entity;

import com.ogbc.archive.model.TopicModel;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name="TOPIC")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class TopicEntity
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="topic")
    @NonNull
    private String name;

    @ManyToMany(mappedBy = "topics")
    private List<ContentEntity> content;

    public TopicEntity(TopicModel model)
    {
        name = model.getName();
    }
}
