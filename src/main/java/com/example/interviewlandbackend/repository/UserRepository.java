package com.example.interviewlandbackend.repository;

import com.example.interviewlandbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Integer> {


}
