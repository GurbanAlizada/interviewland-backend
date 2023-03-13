package com.example.interviewlandbackend.dto.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateContentRequest {

    @NotNull
    private int id;

    @NotBlank
    private String contentName;


    public String getContentName() {
        return contentName;
    }

    public int getId() {
        return id;
    }


}
