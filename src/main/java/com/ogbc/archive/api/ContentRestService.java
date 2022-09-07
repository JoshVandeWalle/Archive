package com.ogbc.archive.api;

import com.ogbc.archive.api.dto.RestDto;
import com.ogbc.archive.api.validator.AlphaNumAndSpaces;
import com.ogbc.archive.api.validator.LettersAndSpaces;
import com.ogbc.archive.model.ContentModel;
import com.ogbc.archive.model.TopicModel;
import com.ogbc.archive.model.PassageModel;
import com.ogbc.archive.service.ContentBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/content")
@CrossOrigin(maxAge = 3600)
@Validated
public class ContentRestService
{
    @Autowired
    ContentBusinessService service;

    @GetMapping("/topic/{topic}")
    public ResponseEntity<RestDto> handleGetByTopic(@PathVariable("topic") @Size(max = 128, message = "Path variable is too long") @LettersAndSpaces String topic)
    {
        List<ContentModel> content = service.retrieveByTopic(new TopicModel(topic));

        return content.isEmpty() ?
                new ResponseEntity<>(new RestDto(null, "No matching content found"), HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(new RestDto(content, "Operation Successful"), HttpStatus.OK);
    }

    @GetMapping("/passage")
    public ResponseEntity<RestDto> handleGetByPassage(@RequestParam("book") @Size(max = 32, message = "Path variable is too long") @AlphaNumAndSpaces String book, @RequestParam("chapter") Integer chapter, @RequestParam(value = "verse", required = false) Integer verse)
    {
        PassageModel passage = new PassageModel(book, chapter, verse);

        List<ContentModel> content = service.retrieveByPassage(passage);

        return content.isEmpty() ?
                new ResponseEntity<>(new RestDto(null, "No matching content found"), HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(new RestDto(content, "Operation Successful"), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<RestDto> handleGetRecent()
    {
        List<ContentModel> content = service.retrieveRecent();

        return content.isEmpty() ?
                new ResponseEntity<>(new RestDto(null, "No content found"), HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(new RestDto(content, "Operation Successful"), HttpStatus.OK);
    }
}
