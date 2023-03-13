package com.example.interviewlandbackend.dto.request;


import javax.validation.constraints.NotBlank;

// Request classlari ucun bos constructor ve get funksiyalarinin varligi kifayetdir
public class CreateContentRequest {

    @NotBlank
    private String contentName;


    // Bosh constructor mutleq olmalidir yoxsa body-den reqeust atmaq olmur
    public String getContentName() {
        return contentName;
    }


}
