package com.ogbc.archive.integration;

import com.ogbc.archive.api.dto.RestDto;
import com.ogbc.archive.model.ContentModel;
import com.ogbc.archive.model.TopicModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class ContentIntegrationTests
{
    // capture the random port used
    @LocalServerPort
    int port;

    // TestRestTemplate that will be used to send requests to the application
    TestRestTemplate restTemplate = new TestRestTemplate();

    // The HTTP header to send with requests
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void storeContentWithoutToken()
    {
        List<TopicModel> topics = new ArrayList<>();
        topics.add(new TopicModel("Salvation"));
        topics.add(new TopicModel("New topic"));
        ContentModel content = new ContentModel(null, LocalDate.now(), "John 3:16", "God shows His love", "Mike Van de Walle", "Oak Grove Baptist Church", null, "https://google.com", topics);

        // instantiate an HttpEntity to send with request
        HttpEntity<ContentModel> entity = new HttpEntity<ContentModel>(content, headers);
        // send request and capture response
        ResponseEntity<RestDto> response = restTemplate.exchange(
                createURLWithPort("/content/store"), HttpMethod.POST, entity, RestDto.class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void retrieveByTopicWithValidTopicThatExists()
    {
        // instantiate an HttpEntity to send with request
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        // send request and capture response
        ResponseEntity<RestDto> response = restTemplate.exchange(
                createURLWithPort("/content/topic/love"), HttpMethod.GET, entity, RestDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().getData().size() > 0);
    }

    @Test
    public void retrieveByTopicWithValidTopicThatDoesNotExist()
    {
        // instantiate an HttpEntity to send with request
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        // send request and capture response
        ResponseEntity<RestDto> response = restTemplate.exchange(
                createURLWithPort("/content/topic/abcdefgh"), HttpMethod.GET, entity, RestDto.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody().getData());
    }

    @Test
    public void retrieveByTopicWithInvalidTopicThatContainsIllegalCharacters()
    {
        // instantiate an HttpEntity to send with request
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        // send request and capture response
        ResponseEntity<RestDto> response = restTemplate.exchange(
                createURLWithPort("/content/topic/abcdefgh^"), HttpMethod.GET, entity, RestDto.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void retrieveByTopicWithInvalidTopicThatIsTooLong()
    {
        // instantiate an HttpEntity to send with request
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        // send request and capture response
        ResponseEntity<RestDto> response = restTemplate.exchange(
                createURLWithPort("/content/topic/abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"), HttpMethod.GET, entity, RestDto.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void retrieveByPassageWithValidPassageWithVerseThatExists()
    {
        // instantiate an HttpEntity to send with request
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        // send request and capture response
        ResponseEntity<RestDto> response = restTemplate.exchange(
                createURLWithPort("/content/passage?book=heb&chapter=10&verse=33"), HttpMethod.GET, entity, RestDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().getData().size() > 0);
    }

    @Test
    public void retrieveByPassageWithValidPassageWithVerseThatDoesNotExist()
    {
        // instantiate an HttpEntity to send with request
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        // send request and capture response
        ResponseEntity<RestDto> response = restTemplate.exchange(
                createURLWithPort("/content/passage?book=test&chapter=10&verse=33"), HttpMethod.GET, entity, RestDto.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody().getData());
    }

    @Test
    public void retrieveByPassageWithValidPassageWithoutVerseThatExists()
    {
        // instantiate an HttpEntity to send with request
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        // send request and capture response
        ResponseEntity<RestDto> response = restTemplate.exchange(
                createURLWithPort("/content/passage?book=heb&chapter=10"), HttpMethod.GET, entity, RestDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().getData().size() > 0);
    }

    @Test
    public void retrieveByPassageWithValidPassageWithoutVerseThatDoesNotExist()
    {
        // instantiate an HttpEntity to send with request
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        // send request and capture response
        ResponseEntity<RestDto> response = restTemplate.exchange(
                createURLWithPort("/content/passage?book=test&chapter=10"), HttpMethod.GET, entity, RestDto.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody().getData());
    }

    @Test
    public void retrieveByPassageWithInvalidBookThatContainsIllegalCharacters()
    {
        // instantiate an HttpEntity to send with request
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        // send request and capture response
        ResponseEntity<RestDto> response = restTemplate.exchange(
                createURLWithPort("/content/passage?book=heb@&chapter=10"), HttpMethod.GET, entity, RestDto.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void retrieveByPassageWithInvalidBookThatIsTooLong()
    {
        // instantiate an HttpEntity to send with request
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        // send request and capture response
        ResponseEntity<RestDto> response = restTemplate.exchange(
                createURLWithPort("/content/passage?book=abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz&chapter=10"), HttpMethod.GET, entity, RestDto.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void retrieveByPassageWithInvalidChapterThatIsOutOfBounds()
    {
        // instantiate an HttpEntity to send with request
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        // send request and capture response
        ResponseEntity<RestDto> response = restTemplate.exchange(
                createURLWithPort("/content/passage?book=heb&chapter=151"), HttpMethod.GET, entity, RestDto.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void retrieveByPassageWithInvalidVerseThatIsOutOfBounds()
    {
        // instantiate an HttpEntity to send with request
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        // send request and capture response
        ResponseEntity<RestDto> response = restTemplate.exchange(
                createURLWithPort("/content/passage?book=heb&chapter=10&verse=-1"), HttpMethod.GET, entity, RestDto.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void retrieveRecent()
    {
        // instantiate an HttpEntity to send with request
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        // send request and capture response
        ResponseEntity<RestDto> response = restTemplate.exchange(
                createURLWithPort("/content"), HttpMethod.GET, entity, RestDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().getData().size() > 0);
    }

    /**
     * This testing utility method builds a full URL from a request URI
     * @param uri the URI to send request to
     * @return String full URL built from URI
     */
    private String createURLWithPort(String uri)
    {
        // build and return URL
        return "http://localhost:" + port + uri;
    }
}
