package com.ogbc.archive.unit.service;

import com.ogbc.archive.data.repository.ContentRepository;
import com.ogbc.archive.model.ContentModel;
import com.ogbc.archive.model.PassageModel;
import com.ogbc.archive.model.TopicModel;
import com.ogbc.archive.service.ContentBusinessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ServiceUnitTests
{
    ContentBusinessService service;

    ContentRepository repository;

    /**
     * This method runs before the test suite
     */
    @BeforeEach
    void init()
    {
        // initialize service to be tested
        service = new ContentBusinessService();
        // mock repository
        repository = Mockito.mock(ContentRepository.class);
        // attach mocked repository to service
        service.setRepository(repository);
    }

    @Test
    public void retrieveByTopic()
    {
        when(repository.findByTopic(any(String.class))).thenReturn(new ArrayList<>());

        List<ContentModel> models = service.retrieveByTopic(new TopicModel("Test"));

        assertEquals(models.size(), 0);
    }

    @Test
    public void retrieveByPassageWithVerse()
    {
        when(repository.findByChapter(any(String.class), any(String.class))).thenReturn(new ArrayList<>());

        List<ContentModel> models = service.retrieveByPassage(new PassageModel("Test", 1, 1));

        assertEquals(models.size(), 0);
    }

    @Test
    public void retrievePassageWithoutVerse()
    {
        when(repository.findByChapter(any(String.class), any(String.class))).thenReturn(new ArrayList<>());

        List<ContentModel> models = service.retrieveByPassage(new PassageModel("Test", 1, null));

        assertEquals(models.size(), 0);
    }

    @Test
    public void retrieveRecent()
    {
        when(repository.findRecent()).thenReturn(new ArrayList<>());

        List<ContentModel> models = service.retrieveRecent();

        assertEquals(models.size(), 0);
    }
}
