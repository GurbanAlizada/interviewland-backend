package com.example.interviewlandbackend.service;


import com.example.interviewlandbackend.dto.request.CreateSectionRequest;
import com.example.interviewlandbackend.dto.request.UpdateSectionRequest;
import com.example.interviewlandbackend.dto.response.SectionDto;
import com.example.interviewlandbackend.exception.SectionNotFoundException;
import com.example.interviewlandbackend.model.Content;
import com.example.interviewlandbackend.model.Section;
import com.example.interviewlandbackend.model.User;
import com.example.interviewlandbackend.repository.SectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;
    private final ContentService contentService;
    private final AuthService authService;



    public SectionService(SectionRepository sectionRepository, ContentService contentService, AuthService authService) {
        this.sectionRepository = sectionRepository;
        this.contentService = contentService;
        this.authService = authService;
    }


    @Transactional
    public void createSection(CreateSectionRequest request) throws AccessDeniedException {


        Content content = contentService.getById(request.getContentId());
        User authenticatedUser = authService.getAuthenticatedUser();


        if (content.getUser().getId() == authenticatedUser.getId() || authenticatedUser.getRole().toString().equals("SUPER_ADMIN")  ){

            Section section = new Section(request.getSectionName() , content);
            sectionRepository.save(section);

        }else{
            throw new AccessDeniedException("ACCESS DENIED ! ");
        }





    }


    @Transactional
    public void updateSection(UpdateSectionRequest request) throws AccessDeniedException {
        Section section = getById(request.getId());
        Content content = contentService.getById(request.getContentId());
        User authenticatedUser = authService.getAuthenticatedUser();


        if (content.getUser().getId() == authenticatedUser.getId() || authenticatedUser.getRole().toString().equals("SUPER_ADMIN")  ){

            section.setSectionName(request.getSectionName());
            section.setContent(content);
            sectionRepository.save(section);


        }else{
            throw new AccessDeniedException("ACCESS DENIED ! ");
        }



    }



    @Transactional
    public void deleteSection(int id) throws AccessDeniedException {
        Section section = getById(id);
        User authenticatedUser = authService.getAuthenticatedUser();

        if (section.getContent().getUser().getId() == authenticatedUser.getId() || authenticatedUser.getRole().toString().equals("SUPER_ADMIN")  ){
            sectionRepository.delete(section);
        }else{
            throw new AccessDeniedException("ACCESS DENIED ! ");
        }

    }



    public List<SectionDto> getAllSections(int contentId) {
        List<Section> sections = sectionRepository.getAllByContent_Id(contentId);
        List<SectionDto> result = sections.stream()
                .map(n -> SectionDto.convert(n))
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
