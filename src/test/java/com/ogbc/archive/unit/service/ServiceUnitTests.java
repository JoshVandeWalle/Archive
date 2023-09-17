package com.ogbc.archive.unit.service;

import com.ogbc.archive.data.repository.ContentRepository;
import com.ogbc.archive.data.repository.TopicRepository;
import com.ogbc.archive.model.ContentModel;
import com.ogbc.archive.model.PassageModel;
import com.ogbc.archive.model.TopicModel;
import com.ogbc.archive.service.ContentActionOutcome;
import com.ogbc.archive.service.ContentBusinessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ServiceUnitTests
{
    ContentBusinessService service;

    ContentRepository contentRepository;

    TopicRepository topicRepository;

    /**
     * This method runs before the test suite
     */
    @BeforeEach
    void init()
    {
        // initialize service to be tested
        service = new ContentBusinessService();
        // mock repositories
        contentRepository = Mockito.mock(ContentRepository.class);
        topicRepository = Mockito.mock(TopicRepository.class);
        // attach mocked repository to service
        service.setContentRepository(contentRepository);
        service.setTopicRepository(topicRepository);
    }

    @Test
    public void store()
    {
        List<TopicModel> topics = new ArrayList<>();
        topics.add(new TopicModel("Salvation"));
        topics.add(new TopicModel("New topic"));
        ContentModel content = new ContentModel(null, LocalDate.now(), "John 3:16", "God shows His love", "Mike Van de Walle", "Oak Grove Baptist Church", null, "https://google.com", topics);

        ContentActionOutcome outcome = service.store(content);

        assertEquals(outcome, ContentActionOutcome.STORED);
    }

    @Test
    public void retrieveByTopic()
    {
        when(contentRepository.findByTopic(any(String.class))).thenReturn(new ArrayList<>());

        List<ContentModel> models = service.retrieveByTopic(new TopicModel("Test"));

        assertEquals(models.size(), 0);
    }

    @Test
    public void retrieveByPassageWithVerse()
    {
        when(contentRepository.findByChapter(any(String.class), any(String.class))).thenReturn(new ArrayList<>());

        List<ContentModel> models = service.retrieveByPassage(new PassageModel("Test", 1, 1));

        assertEquals(models.size(), 0);
    }

    @Test
    public void retrievePassageWithoutVerse()
    {
        when(contentRepository.findByChapter(any(String.class), any(String.class))).thenReturn(new ArrayList<>());

        List<ContentModel> models = service.retrieveByPassage(new PassageModel("Test", 1, null));

        assertEquals(models.size(), 0);
    }

    @Test
    public void retrieveRecent()
    {
        when(contentRepository.findRecent()).thenReturn(new ArrayList<>());

        List<ContentModel> models = service.retrieveRecent();

        assertEquals(models.size(), 0);
    }
}
