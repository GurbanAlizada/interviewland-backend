package com.example.interviewlandbackend.controller;


import com.example.interviewlandbackend.dto.request.CreateContentRequest;
import com.example.interviewlandbackend.dto.request.UpdateContentRequest;
import com.example.interviewlandbackend.dto.response.ContentDto;
import com.example.interviewlandbackend.service.ContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/contents")
public class ContentRestController {

    private final ContentService contentService;

    public ContentRestController(ContentService contentService) {
        this.contentService = contentService;
    }


    @PostMapping
    public ResponseEntity<Void> createContent(@RequestBody @Valid CreateContentRequest request){
        contentService.createContent(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateContent(@Valid @RequestBody UpdateContentRequest request){
        contentService.updateContent(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable int id){
        contentService.deleteContent(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<List<ContentDto>> getAllContent(){
        return ResponseEntity.ok(contentService.getAllContent());
    }


//    @GetMapping("/content/{id}")
//    public ResponseEntity<ContentDto> getContentById(@PathVariable int id){
//        return ResponseEntity.ok(contentService.getContentById(id));
//    }



//    @GetMapping
//    public ResponseEntity<List<ContentDto>> search(@RequestParam String search){
//        return ResponseEntity.ok(contentService.search(search));
//    }





}
