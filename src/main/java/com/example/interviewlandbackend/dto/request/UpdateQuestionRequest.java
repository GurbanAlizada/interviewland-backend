package com.example.interviewlandbackend.dto.request;


public class UpdateQuestionRequest {

    private int questionId;

    private String questionTitle;

    private String description;

    private String sourceCode;

    private int sectionId;

    public int getQuestionId() {
        return questionId;
    }

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
