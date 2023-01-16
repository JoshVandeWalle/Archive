package com.ogbc.archive.unit.api;

import com.ogbc.archive.api.ContentRestService;
import com.ogbc.archive.api.dto.RestDto;
import com.ogbc.archive.model.ContentModel;
import com.ogbc.archive.model.PassageModel;
import com.ogbc.archive.model.TopicModel;
import com.ogbc.archive.service.ContentBusinessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ApiUnitTests
{
    ContentRestService restService;

    ContentBusinessService businessService;

    /**
     * This method runs before the test suite
     */
    @BeforeEach
    void initUseCase()
    {
        // initialize REST service
        restService = new ContentRestService();
        // mock business logic
        businessService = Mockito.mock(ContentBusinessService.class);
        // attach mocked business logic service to the REST service being tested
        restService.setService(businessService);
    }

    @Test
    public void getByTopicWithTopicThatExists()
    {
        List<ContentModel> models = new ArrayList<>();
        models.add(new ContentModel());
        when(businessService.retrieveByTopic(any(TopicModel.class))).thenReturn(models);

        ResponseEntity<RestDto<ContentModel>> responseEntity = restService.handleGetByTopic("Test");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertFalse(responseEntity.getBody().getData().isEmpty());
        assertFalse(true);
    }

    @Test
    public void getByTopicWithTopicThatDoesNotExist()
    {
        when(businessService.retrieveByTopic(any(TopicModel.class))).thenReturn(new ArrayList<>());

        ResponseEntity<RestDto<ContentModel>> responseEntity = restService.handleGetByTopic("Test");

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody().getData());
    }

    @Test
    public void getByPassageWithPassageIncludingVerseThatExists()
    {
        List<ContentModel> models = new ArrayList<>();
        models.add(new ContentModel());
        when(businessService.retrieveByPassage(any(PassageModel.class))).thenReturn(models);

        ResponseEntity<RestDto<ContentModel>> responseEntity = restService.handleGetByPassage("Test", 1, 1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertFalse(responseEntity.getBody().getData().isEmpty());
    }

    @Test
    public void getByPassageWithPassageIncludingVerseThatDoesNotExist()
    {
        when(businessService.retrieveByPassage(any(PassageModel.class))).thenReturn(new ArrayList<>());

        ResponseEntity<RestDto<ContentModel>> responseEntity = restService.handleGetByPassage("Test", 1, 1);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody().getData());
    }

    @Test
    public void getByPassageWithPassageExcludingVerseThatExists()
    {
        List<ContentModel> models = new ArrayList<>();
        models.add(new ContentModel());
        when(businessService.retrieveByPassage(any(PassageModel.class))).thenReturn(models);

        ResponseEntity<RestDto<ContentModel>> responseEntity = restService.handleGetByPassage("Test", 1, null);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertFalse(responseEntity.getBody().getData().isEmpty());
    }

    @Test
    public void getByPassageWithPassageExcludingVerseThatDoesNotExist()
    {
        when(businessService.retrieveByPassage(any(PassageModel.class))).thenReturn(new ArrayList<>());

        ResponseEntity<RestDto<ContentModel>> responseEntity = restService.handleGetByPassage("Test", 1, null);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody().getData());
    }

    @Test
    public void getByRecentWhenContentExists()
    {
        List<ContentModel> models = new ArrayList<>();
        models.add(new ContentModel());
        when(businessService.retrieveRecent()).thenReturn(models);

        ResponseEntity<RestDto<ContentModel>> responseEntity = restService.handleGetRecent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertFalse(responseEntity.getBody().getData().isEmpty());
    }

    @Test
    public void getByRecentWhenNoContentExists()
    {
        when(businessService.retrieveRecent()).thenReturn(new ArrayList<>());

        ResponseEntity<RestDto<ContentModel>> responseEntity = restService.handleGetRecent();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody().getData());
    }
}
