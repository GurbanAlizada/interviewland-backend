package com.example.interviewlandbackend.service;

import com.example.interviewlandbackend.dto.request.CreateQuestionRequest;
import com.example.interviewlandbackend.dto.request.UpdateQuestionRequest;
import com.example.interviewlandbackend.dto.response.QuestionDto;
import com.example.interviewlandbackend.exception.QuestionNotFoundException;
import com.example.interviewlandbackend.model.Question;
import com.example.interviewlandbackend.model.Section;
import com.example.interviewlandbackend.model.User;
import com.example.interviewlandbackend.repository.QuestionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final SectionService sectionService;
    private final AuthService authService;


    public QuestionService(QuestionRepository questionRepository, SectionService sectionService, AuthService authService) {
        this.questionRepository = questionRepository;
        this.sectionService = sectionService;
        this.authService = authService;
    }



    @Transactional
    public void createQuestion(CreateQuestionRequest request) throws AccessDeniedException {
        Section section = sectionService.getById(request.getSectionId());
        User authenticatedUser = authService.getAuthenticatedUser();

        if (authenticatedUser.getId() == section.getContent().getUser().getId() || authenticatedUser.getRole().toString().equals("SUPER_ADMIN") ){
            Question question = new Question(
                    request.getQuestionTitle() ,
                    request.getDescription() ,
                    request.getSourceCode() ,
                    section);
            questionRepository.save(question);
        }else{
            throw new AccessDeniedException("ACCESS DENIED ! ");
        }




    }


    @Transactional
    public void updateQuestion(UpdateQuestionRequest request) throws AccessDeniedException {
        Question question = getById(request.getQuestionId());
        Section section = sectionService.getById(request.getSectionId());
        User authenticatedUser = authService.getAuthenticatedUser();

        if (authenticatedUser.getId() == section.getContent().getUser().getId() || authenticatedUser.getRole().toString().equals("SUPER_ADMIN") ){
            question.setQuestionTitle(request.getQuestionTitle());
            question.setDescription(request.getDescription());
            question.setSourceCode(request.getSourceCode());
            question.setSection(section);
            questionRepository.save(question);
        }else{
            throw new AccessDeniedException("ACCESS DENIED ! ");
        }

    }



    @Transactional
    public void deleteQuestion(int id) throws AccessDeniedException {
        Question question = getById(id);
        User authenticatedUser = authService.getAuthenticatedUser();


        if (authenticatedUser.getId() == question.getSection().getContent().getUser().getId() || authenticatedUser.getRole().toString().equals("SUPER_ADMIN") ){
            questionRepository.delete(question);
        }else{
            throw new AccessDeniedException("ACCESS DENIED ! ");
        }
    }



    public List<QuestionDto> getAllQuestionByContentId(int sectionId , int pageNo , int size) {

        Sort sort = Sort.by(Sort.Direction.ASC , "id");

        List<Question> questions = questionRepository.getAllBySection_Id(sectionId , PageRequest.of(pageNo - 1, size , sort));

        List<QuestionDto> result = questions.stream()
                .map(n->QuestionDto.convert(n))
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



