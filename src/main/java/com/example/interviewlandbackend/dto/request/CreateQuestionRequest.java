package com.example.interviewlandbackend.dto.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateQuestionRequest {

    @NotBlank
    private String questionTitle;

    @NotBlank
    private String description;

    private String sourceCode;

    @NotNull
    private int sectionId;


    public String getQuestionTitle() {
        return questionTitle;
    }

    public String getDescription() {
        return description;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public int getSectionId() {
        return sectionId;
    }
}
