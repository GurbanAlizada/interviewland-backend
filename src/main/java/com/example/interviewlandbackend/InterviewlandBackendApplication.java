package com.example.interviewlandbackend;

import com.example.interviewlandbackend.model.Role;
import com.example.interviewlandbackend.model.User;
import com.example.interviewlandbackend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class InterviewlandBackendApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public InterviewlandBackendApplication(UserRepository userRepository,
                                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public static void main(String[] args) {
        SpringApplication.run(InterviewlandBackendApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("super-admin")==null){
            User user = new User(
                    "super-admin" ,
                    bCryptPasswordEncoder.encode("12345678") ,
                    "qurbanelizade77@gmail.com" ,
                    Role.SUPER_ADMIN
            );
            userRepository.save(user);
        }

    }


}
