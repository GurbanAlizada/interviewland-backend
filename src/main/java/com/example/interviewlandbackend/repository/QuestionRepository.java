package com.example.interviewlandbackend.repository;

import com.example.interviewlandbackend.model.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {


    List<Question> getAllBySection_Id(int sectionId , Pageable pageable);

}
