package com.example.interviewlandbackend.service;


import com.example.interviewlandbackend.dto.request.CreateContentRequest;
import com.example.interviewlandbackend.dto.request.UpdateContentRequest;
import com.example.interviewlandbackend.dto.response.ContentDto;
import com.example.interviewlandbackend.exception.ContentNotFoundException;
import com.example.interviewlandbackend.model.Content;
import com.example.interviewlandbackend.repository.ContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentService {

    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }


    @Transactional
    public void createContent(CreateContentRequest request) {
        Content content = new Content(request.getContentName());
        contentRepository.save(content);
    }


    @Transactional
    public void updateContent(UpdateContentRequest request) {
        Content content = getById(request.getId());
        content.setContentName(request.getContentName());
        contentRepository.save(content);
    }


    @Transactional
    public void deleteContent(int id) {
        Content content = getById(id);
        contentRepository.delete(content);
    }


    public List<ContentDto> getAllContent() {
        List<Content> contentList = contentRepository.findAll();
        List<ContentDto> result = contentList.stream()
                .map(n -> new ContentDto(
                        n.getId() ,
                        n.getContentName() )
                )
                .collect(Collectors.toList());
        return result;
    }

    public ContentDto getContentById(int id) {
        Content content = getById(id);
        ContentDto result = new ContentDto(
                content.getId()
                , content.getContentName()
        );
        return result;
    }


    public List<ContentDto> search(String keyWord) {
        List<Content> contents = contentRepository.searchForContentName(keyWord);
        List<ContentDto> result = contents
                .stream().map(n ->
                        new ContentDto(
                                n.getId(),
                                n.getContentName()
                        )
                )
                .collect(Collectors.toList());
        return result;
    }


    protected Content getById(int id){
        Content fromDb = contentRepository.findById(id)
                .orElseThrow(
                        () -> new ContentNotFoundException("could not found content by id : " + id)
                );
        return fromDb;
    }



}
