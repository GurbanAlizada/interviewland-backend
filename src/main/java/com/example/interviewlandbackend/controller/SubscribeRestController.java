package com.example.interviewlandbackend.controller;


import com.example.interviewlandbackend.service.SubscribeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/v1/subscribe")
public class SubscribeRestController {

    private final SubscribeService subscribeService;


    public SubscribeRestController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    @PostMapping
    public ResponseEntity<Void> subscribeToContent(@RequestParam String email , @RequestParam int contentId){
        subscribeService.subscribeToContent(email , contentId);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/sendEmail/{id}")
    public ResponseEntity<String> sendEmailByContentId(@PathVariable int id , String link) throws MessagingException, UnsupportedEncodingException {
        int userSize = subscribeService.sendEmailByContentId(id , link);
        return ResponseEntity.ok("Istifadecilere mail gonderildi! .Mail gonderilmis istifadeci sayi : " + userSize);
    }





}
