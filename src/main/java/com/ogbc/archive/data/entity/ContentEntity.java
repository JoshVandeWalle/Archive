package com.ogbc.archive.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name="CONTENT")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContentEntity
{
    @Id
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

}
