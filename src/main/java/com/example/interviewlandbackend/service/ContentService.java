package com.example.interviewlandbackend.service;


import com.example.interviewlandbackend.adapters.inter.CloudinaryServiceInter;
import com.example.interviewlandbackend.dto.request.CreateContentRequest;
import com.example.interviewlandbackend.dto.request.UpdateContentRequest;
import com.example.interviewlandbackend.dto.response.ContentDto;
import com.example.interviewlandbackend.exception.ContentNotFoundException;
import com.example.interviewlandbackend.model.Content;
import com.example.interviewlandbackend.model.Image;
import com.example.interviewlandbackend.repository.ContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final CloudinaryServiceInter cloudinaryServiceInter;

    public ContentService(ContentRepository contentRepository, CloudinaryServiceInter cloudinaryServiceInter) {
        this.contentRepository = contentRepository;
        this.cloudinaryServiceInter = cloudinaryServiceInter;
    }


    // after security refactor user
    @Transactional
    public void createContent(CreateContentRequest request) {
        Map<String, String> map = cloudinaryServiceInter.uploadImage(request.getImage());
        final String imageUrl = map.get("secure_url");
        final String publishId = map.get("public_id");
        Image image = new Image(imageUrl , publishId );
        Content content = new Content(request.getContentName() , request.getDescription() , image , null);
        contentRepository.save(content);
    }


    // after refactor security
    @Transactional
    public void updateContent(UpdateContentRequest request) throws IOException {
        Content content = getById(request.getId());
        content.setDescription(request.getDescription());
        content.setContentName(request.getContentName());
        if (request.getPhoto() != null){
            cloudinaryServiceInter.deleteImage(content.getImage().getPublishId());
            Map<String, String> map = cloudinaryServiceInter.uploadImage(request.getPhoto());
            final String imageUrl = map.get("secure_url");
            final String publishId = map.get("public_id");
            Image image = new Image(imageUrl , publishId);
            content.setImage(image);
        }
        contentRepository.save(content);
    }





    @Transactional
    // after refactor security
    public void deleteContent(int id) {
        Content content = getById(id);
        contentRepository.delete(content);
    }


    public List<ContentDto> getAllContent() {
        List<Content> contentList = contentRepository.findAll();
        List<ContentDto> result = contentList
                .stream()
                .map(n -> ContentDto.convert(n))
                .collect(Collectors.toList());
        return result;
    }


    public ContentDto getContentById(int id) {
        Content content = getById(id);
        ContentDto result = ContentDto.convert(content);
        return result;
    }


    public List<ContentDto> search(String keyWord) {
        List<Content> contents = contentRepository.searchForContentName(keyWord);
        List<ContentDto> result = contents
                .stream()
                .map(n -> ContentDto.convert(n))
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
