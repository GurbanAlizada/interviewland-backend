package com.example.interviewlandbackend.service;


import com.example.interviewlandbackend.dto.request.CreateUserRequest;
import com.example.interviewlandbackend.exception.UserNotFoundException;
import com.example.interviewlandbackend.model.Role;
import com.example.interviewlandbackend.model.User;
import com.example.interviewlandbackend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Transactional
    public void createAdmin(CreateUserRequest request) {
        String password = bCryptPasswordEncoder.encode(request.getPassword());
        User user = new User(
                request.getUsername(),
                password ,
                request.getEmail(),
                Role.ADMIN
        );
        userRepository.save(user);
    }



    public User findByUserName(String username){
        User fromDb = userRepository.findByUsername(username);

        if (fromDb == null){
            throw new UserNotFoundException("could not found user");
        }

        return fromDb;
    }



}
