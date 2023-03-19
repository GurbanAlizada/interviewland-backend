package com.example.interviewlandbackend.service;


import com.example.interviewlandbackend.model.Content;
import com.example.interviewlandbackend.model.Subscribe;
import com.example.interviewlandbackend.repository.SubscribeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final ContentService contentService;
    private final MailService mailService;


    public SubscribeService(SubscribeRepository subscribeRepository, ContentService contentService, MailService mailService) {
        this.subscribeRepository = subscribeRepository;
        this.contentService = contentService;
        this.mailService = mailService;
    }


    @Transactional
    public void subscribeToContent(String email  , int contentId) {
        Content content = contentService.getById(contentId);
        Subscribe subscribe = new Subscribe(email,content);
        subscribeRepository.save(subscribe);

    }




    public int sendEmailByContentId(int id , String link) throws MessagingException, UnsupportedEncodingException {
        List<Subscribe> subscribes = subscribeRepository.findByContent_Id(id);
        for (Subscribe element : subscribes){
            mailService.sendEmail(element.getEmail(),link , element.getContent().getContentName());
        }
        return subscribes.size();
    }
}
