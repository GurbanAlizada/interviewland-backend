package com.example.interviewlandbackend.service;


import com.example.interviewlandbackend.model.Subscribe;
import com.example.interviewlandbackend.repository.SubscribeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final MailService mailService;


    public SubscribeService(SubscribeRepository subscribeRepository, MailService mailService) {
        this.subscribeRepository = subscribeRepository;
        this.mailService = mailService;
    }


    @Transactional
    public void subscribe(String email) {
        Subscribe subscribe = new Subscribe(email);
        subscribeRepository.save(subscribe);
    }


    public int sendEmailAllSubscribedUsers() {
        List<Subscribe> subscribes = subscribeRepository.findAll();
        for (Subscribe element : subscribes){
            mailService.sendMail(element.getEmail());
        }
        return subscribes.size();
    }
}
