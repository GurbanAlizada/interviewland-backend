package com.example.interviewlandbackend.controller;

import com.example.interviewlandbackend.dto.request.CreateSectionRequest;
import com.example.interviewlandbackend.dto.request.UpdateSectionRequest;
import com.example.interviewlandbackend.dto.response.SectionDto;
import com.example.interviewlandbackend.service.SectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/sections")
public class SectionRestController {


    private final SectionService sectionService;

    public SectionRestController(SectionService sectionService) {
        this.sectionService = sectionService;
    }


    @PostMapping
    public ResponseEntity<Void> createSection(@RequestBody @Valid CreateSectionRequest request){
        sectionService.createSection(request);
        return ResponseEntity.ok().build();
    }


    @PutMapping
    public ResponseEntity<Void> updateSection(@RequestBody @Valid UpdateSectionRequest request){
        sectionService.updateSection(request);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable int id){
        sectionService.deleteSection(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<SectionDto>> getAllSections(@RequestParam int contentId){
        return  ResponseEntity.ok(sectionService.getAllSections(contentId));
    }









}
