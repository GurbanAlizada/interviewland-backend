package com.example.interviewlandbackend.repository;

import com.example.interviewlandbackend.model.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {


    List<Subscribe> findByContent_Id(int id);


}
