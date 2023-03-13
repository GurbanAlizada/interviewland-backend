package com.example.interviewlandbackend.controller;


import com.example.interviewlandbackend.service.SubscribeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/subscribe")
public class SubscribeRestController {

    private final SubscribeService subscribeService;


    public SubscribeRestController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    // All users
    @PostMapping
    public ResponseEntity<Void> subscribe(@RequestParam String email){
        subscribeService.subscribe(email);
        return ResponseEntity.ok().build();
    }


    // Only Admin
    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmailAllSubscribedUsers(){
        int userSize = subscribeService.sendEmailAllSubscribedUsers();
        return ResponseEntity.ok("Istifadecilere mail gonderildi! .Mail gonderilmis istifadeci sayi : " + userSize);
    }





}
