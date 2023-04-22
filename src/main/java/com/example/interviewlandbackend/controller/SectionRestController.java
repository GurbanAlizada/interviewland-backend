package com.example.interviewlandbackend.controller;

import com.example.interviewlandbackend.dto.request.CreateSectionRequest;
import com.example.interviewlandbackend.dto.request.UpdateSectionRequest;
import com.example.interviewlandbackend.dto.response.SectionDto;
import com.example.interviewlandbackend.service.SectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/v1/sections")
@CrossOrigin
public class SectionRestController {


    private final SectionService sectionService;

    public SectionRestController(SectionService sectionService) {
        this.sectionService = sectionService;
    }


    @PostMapping
    public ResponseEntity<Void> createSection(@RequestBody @Valid CreateSectionRequest request) throws AccessDeniedException {
        sectionService.createSection(request);
        return ResponseEntity.ok().build();
    }


    @PutMapping
    public ResponseEntity<Void> updateSection(@RequestBody @Valid UpdateSectionRequest request) throws AccessDeniedException {
        sectionService.updateSection(request);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable int id) throws AccessDeniedException {
        sectionService.deleteSection(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<SectionDto>> getAllSections(@RequestParam int contentId){
        return  ResponseEntity.ok(sectionService.getAllSections(contentId));
    }









}
