package com.example.interviewlandbackend.controller;


import com.example.interviewlandbackend.dto.request.CreateUserRequest;
import com.example.interviewlandbackend.service.UserService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/users")
@CrossOrigin
public class UserRestController {

    private final UserService userService;


    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    //TODO email and username already exists exception

    @PostMapping("/admin-register")
    public ResponseEntity<Void> createAdmin(@Valid @RequestBody CreateUserRequest request){
        userService.createAdmin(request);
        return ResponseEntity.ok().build();
    }











}
