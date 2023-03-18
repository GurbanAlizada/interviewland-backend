package com.example.interviewlandbackend.service;


import com.example.interviewlandbackend.dto.request.LoginRequest;
import com.example.interviewlandbackend.dto.response.TokenResponseDto;
import com.example.interviewlandbackend.exception.UserNotFoundException;
import com.example.interviewlandbackend.model.User;
import com.example.interviewlandbackend.repository.UserRepository;
import com.example.interviewlandbackend.util.TokenGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {



    private final UserService userService;
    private final UserRepository userRepository;
    private final TokenGenerator tokenGenerator;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserService userService,
                       UserRepository userRepository, TokenGenerator tokenGenerator,
                       AuthenticationManager authenticationManager,
                        BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public TokenResponseDto login(LoginRequest request){

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername() ,
                            request.getPassword())
            );

            return new TokenResponseDto(tokenGenerator.generateToken(authentication));
        }catch (final Exception exception){

            throw new UserNotFoundException("could not found user");
        }


    }





    protected User getAuthenticatedUser(){
        String username = ( (UserDetails)  SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userService.findByUserName(username);
        return user;
    }




}
