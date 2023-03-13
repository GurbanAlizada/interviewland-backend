package com.example.interviewlandbackend.dto.response;

public class QuestionDto {

    private int questionId;

    private String title;

    private String description;

    private String sourceCode;

    private int sectionId;

    public QuestionDto(int questionId, String title,
                       String description, String sourceCode, int sectionId) {
        this.questionId = questionId;
        this.title = title;
        this.description = description;
        this.sourceCode = sourceCode;
        this.sectionId = sectionId;
    }


    public String getTitle() {
        return title;
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

    public int getQuestionId() {
        return questionId;
    }
}
