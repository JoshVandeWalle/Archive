package com.ogbc.archive.model;

import com.ogbc.archive.data.entity.ContentEntity;
import com.ogbc.archive.data.entity.TopicEntity;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class ContentModel
{
    private Long id;

    @NonNull
    private LocalDate date;

    @NonNull
    @NotBlank
    private String passage;

    @NonNull
    @NotBlank
    private String title;

    @NonNull
    private String speaker;

    @NonNull
    private String venue;

    private String notes;

    @NonNull
    @URL
    private String link;

    List<TopicModel> topics;

    public ContentModel(ContentEntity entity)
    {
        id = entity.getId();
        date = entity.getDate();
        passage = entity.getPassage();
        title = entity.getTitle();
        speaker = entity.getSpeaker();
        venue = entity.getVenue();
        notes = entity.getNotes();
        link = entity.getLink();
        topics = new ArrayList<>();

        for (TopicEntity topicEntity : entity.getTopics())
        {
            topics.add(new TopicModel(topicEntity));
        }
    }
}
