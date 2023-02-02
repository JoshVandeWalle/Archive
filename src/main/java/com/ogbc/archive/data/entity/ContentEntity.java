package com.ogbc.archive.data.entity;

import com.ogbc.archive.model.ContentModel;
import com.ogbc.archive.model.TopicModel;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name="CONTENT")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContentEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String passage;

    private String title;

    private String speaker;

    private String venue;

    private String notes;

    private String link;

    @ManyToMany
    @JoinTable(name = "CONTENT_TOPIC", joinColumns = {@JoinColumn(name = "content_id")}, inverseJoinColumns = {@JoinColumn(name = "topic_id")})
    List<TopicEntity> topics;

    public ContentEntity(ContentModel model)
    {
        id = model.getId();
        date = model.getDate();
        passage = model.getPassage();
        title = model.getTitle();
        speaker = model.getSpeaker();
        venue = model.getVenue();
        notes = model.getNotes();
        link = model.getLink();
        topics = new ArrayList<>();
    }

}
