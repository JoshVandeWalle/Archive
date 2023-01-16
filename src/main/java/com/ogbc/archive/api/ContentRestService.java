package com.ogbc.archive.api;

import com.ogbc.archive.api.dto.RestDto;
import com.ogbc.archive.api.validator.*;
import com.ogbc.archive.model.ContentModel;
import com.ogbc.archive.model.TopicModel;
import com.ogbc.archive.model.PassageModel;
import com.ogbc.archive.service.ContentBusinessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/content")
@CrossOrigin(maxAge = 3600)
@Validated
@Setter
@Tag(name = "content", description = "The Content API")
public class ContentRestService
{
    @Autowired
    ContentBusinessService service;

    @GetMapping("/topic/{topic}")
    @Operation(summary = "Find content by topic", description = "Finds content by a topic covered in that content", tags = { "content" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = RestDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid topic provided",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "No content found for provided topic",
                    content = @Content(schema = @Schema(implementation = RestDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal error",
                    content = @Content(schema = @Schema(implementation = RestDto.class)))
    })
    public ResponseEntity<RestDto<ContentModel>> handleGetByTopic(@PathVariable("topic") @Topic String topic)
    {
        List<ContentModel> content = service.retrieveByTopic(new TopicModel(topic));

        return content.isEmpty() ?
                new ResponseEntity<>(new RestDto(null, "No matching content found"), HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(new RestDto(content, "Operation Successful"), HttpStatus.OK);
    }

    @GetMapping("/passage")
    @Operation(summary = "Find content by passage", description = "Finds content by a scripture passage covered in that content", tags = { "content" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = RestDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid passage provided",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "No content found for provided passage",
                    content = @Content(schema = @Schema(implementation = RestDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal error",
                    content = @Content(schema = @Schema(implementation = RestDto.class)))
    })
    public ResponseEntity<RestDto<ContentModel>> handleGetByPassage(@RequestParam("book") @Book String book, @RequestParam("chapter") @Chapter Integer chapter, @RequestParam(value = "verse", required = false) @Verse Integer verse)
    {
        PassageModel passage = new PassageModel(book, chapter, verse);

        List<ContentModel> content = service.retrieveByPassage(passage);

        return content.isEmpty() ?
                new ResponseEntity<>(new RestDto(null, "No matching content found"), HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(new RestDto(content, "Operation Successful"), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Find recent content", description = "Finds the most recent content", tags = { "content" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = RestDto.class))),
            @ApiResponse(responseCode = "404", description = "No content found",
                    content = @Content(schema = @Schema(implementation = RestDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal error",
                    content = @Content(schema = @Schema(implementation = RestDto.class)))
    })
    public ResponseEntity<RestDto<ContentModel>> handleGetRecent()
    {
        List<ContentModel> content = service.retrieveRecent();

        return content.isEmpty() ?
                new ResponseEntity<>(new RestDto(null, "No content found"), HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(new RestDto(content, "Operation Successful"), HttpStatus.OK);
    }
}
