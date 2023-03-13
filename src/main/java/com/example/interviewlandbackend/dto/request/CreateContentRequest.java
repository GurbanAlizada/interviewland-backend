package com.example.interviewlandbackend.dto.request;


import javax.validation.constraints.NotBlank;

public class CreateContentRequest {

    @NotBlank
    private String contentName;

    public CreateContentRequest(String contentName) {
        this.contentName = contentName;
    }


    public String getContentName() {
        return contentName;
    }


}
