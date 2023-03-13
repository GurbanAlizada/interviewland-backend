package com.example.interviewlandbackend.service;

import com.example.interviewlandbackend.dto.request.CreateQuestionRequest;
import com.example.interviewlandbackend.dto.request.UpdateQuestionRequest;
import com.example.interviewlandbackend.dto.response.QuestionDto;
import com.example.interviewlandbackend.exception.QuestionNotFoundException;
import com.example.interviewlandbackend.model.Content;
import com.example.interviewlandbackend.model.Question;
import com.example.interviewlandbackend.model.Section;
import com.example.interviewlandbackend.repository.QuestionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final SectionService sectionService;


    public QuestionService(QuestionRepository questionRepository, SectionService sectionService) {
        this.questionRepository = questionRepository;
        this.sectionService = sectionService;
    }



    @Transactional
    public void createQuestion(CreateQuestionRequest request) {
        Section section = sectionService.getById(request.getSectionId());
        Question question = new Question(request.getQuestionTitle() ,
                request.getDescription() , request.getSourceCode() , section);
        questionRepository.save(question);
    }


    @Transactional
    public void updateQuestion(UpdateQuestionRequest request) {
        Question question = getById(request.getQuestionId());
        Section section = sectionService.getById(request.getSectionId());
        question.setQuestionTitle(request.getQuestionTitle());
        question.setDescription(request.getDescription());
        question.setSourceCode(request.getSourceCode());
        question.setSection(section);
        questionRepository.save(question);
    }



    @Transactional
    public void deleteQuestion(int id) {
        Question question = getById(id);
        questionRepository.delete(question);
    }



    public List<QuestionDto> getAllQuestionByContentId(int sectionId , int pageNo , int size) {

        Sort sort = Sort.by(Sort.Direction.ASC , "id");

        List<Question> questions = questionRepository.getAllBySection_Id(sectionId , PageRequest.of(pageNo - 1, size , sort));

        List<QuestionDto> result = questions.stream()
                .map(n -> new QuestionDto(
                        n.getId(),
                        n.getQuestionTitle(),
                        n.getDescription(),
                        n.getSourceCode(),
                        n.getSection().getId()
                        )
                )
                .collect(Collectors.toList());
        return result;
    }




    protected  Question getById(int id){
        return questionRepository.findById(id)
                .orElseThrow(
                        () -> new QuestionNotFoundException("could not found by question id : "  + id)
                );
    }






}



