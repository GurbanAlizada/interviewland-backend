package com.example.interviewlandbackend.service;


import com.example.interviewlandbackend.dto.request.CreateSectionRequest;
import com.example.interviewlandbackend.dto.request.UpdateSectionRequest;
import com.example.interviewlandbackend.dto.response.SectionDto;
import com.example.interviewlandbackend.exception.SectionNotFoundException;
import com.example.interviewlandbackend.model.Content;
import com.example.interviewlandbackend.model.Section;
import com.example.interviewlandbackend.repository.SectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;
    private final ContentService contentService;

    public SectionService(SectionRepository sectionRepository, ContentService contentService) {
        this.sectionRepository = sectionRepository;
        this.contentService = contentService;
    }


    @Transactional
    public void createSection(CreateSectionRequest request) {
        Content content = contentService.getById(request.getContentId());
        Section section = new Section(request.getSectionName() , content);
        sectionRepository.save(section);
    }


    @Transactional
    public void updateSection(UpdateSectionRequest request) {
        Section section = getById(request.getId());
        Content content = contentService.getById(request.getContentId());
        section.setSectionName(request.getSectionName());
        section.setContent(content);
        sectionRepository.save(section);
    }



    @Transactional
    public void deleteSection(int id) {
        Section section = getById(id);
        sectionRepository.delete(section);
    }


    public List<SectionDto> getAllSections(int contentId) {
        List<Section> sections = sectionRepository.getAllByContent_Id(contentId);
        List<SectionDto> result = sections.stream()
                .map(n -> new SectionDto(
                        n.getId() ,
                        n.getSectionName())
                )
                .collect(Collectors.toList());
        return result;
    }



    protected Section getById(int id){
        Section section = sectionRepository.findById(id)
                .orElseThrow(
                        () -> new SectionNotFoundException("could not found by id : " + id)
                );
        return section;
    }






}
