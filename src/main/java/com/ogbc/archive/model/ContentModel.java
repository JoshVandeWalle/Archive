package com.ogbc.archive.model;

import com.ogbc.archive.data.entity.ContentEntity;
import com.ogbc.archive.data.entity.TopicEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContentModel
{
    @Schema(description = "Content ID", example = "37")
    private Long id;

    @Schema(description = "Date when sermon or other content was presented", example = "2022-11-27")
    private LocalDate date;

    @NonNull
    @NotBlank
    @Size(max = 64)
    @Schema(description = "Reference of Bible passage covered", example = "John 3:16")
    private String passage;

    @NonNull
    @NotBlank
    @Size(max = 128)
    @Schema(description = "Title of message or other content", example = "God Sends His Son")
    private String title;

    @Size(max = 256)
    @Schema(description = "Name of the speaker", example = "Mike Van De Walle")
    private String speaker;

    @Size(max = 256)
    @Schema(description = "Place where sermon or other content was presented", example = "Oak Grove Baptist Church")
    private String venue;

    @Size(max = 1028)
    @Schema(description = "Notes on the sermon or other content")
    private String notes;

    @URL
    @Size(max = 512)
    @Schema(description = "Hyperlink to a video recording, audio-only recording, or readout of sermon or other content as appropriate", example = "https://www.youtube.com/watch?v=skUP9O2OGIo&t=17s")
    private String link;

    @Schema(description = "List of Topics covered in sermon or other content")
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
