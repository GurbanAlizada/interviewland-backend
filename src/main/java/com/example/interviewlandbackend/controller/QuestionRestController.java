package com.example.interviewlandbackend.controller;


import com.example.interviewlandbackend.dto.request.CreateQuestionRequest;
import com.example.interviewlandbackend.dto.request.UpdateQuestionRequest;
import com.example.interviewlandbackend.dto.response.QuestionDto;
import com.example.interviewlandbackend.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/questions")
public class QuestionRestController {

    private final QuestionService questionService;


    public QuestionRestController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @PostMapping
    public ResponseEntity<Void> createQuestion(@Valid @RequestBody CreateQuestionRequest request){
        questionService.createQuestion(request);
        return ResponseEntity.ok().build();
    }


    @PutMapping
    public ResponseEntity<Void> updateQuestion(@Valid @RequestBody UpdateQuestionRequest request){
        questionService.updateQuestion(request);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable int id){
        questionService.deleteQuestion(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<List<QuestionDto>> getAllQuestionByContentId(@RequestParam int sectionId , @RequestParam int pageNo , @RequestParam int size){
        return ResponseEntity.ok(questionService.getAllQuestionByContentId(sectionId , pageNo , size));
    }







}
