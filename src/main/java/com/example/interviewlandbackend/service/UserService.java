package com.example.interviewlandbackend.service;


import com.example.interviewlandbackend.dto.request.CreateUserRequst;
import com.example.interviewlandbackend.model.Role;
import com.example.interviewlandbackend.model.User;
import com.example.interviewlandbackend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public void createUser(CreateUserRequst request) {
        String password = request.getPassword();
        User user = new User(
                request.getUsername(),
                password ,
                Role.USER
        );
        userRepository.save(user);
    }



}
