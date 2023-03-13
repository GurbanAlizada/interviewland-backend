package com.example.interviewlandbackend.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "questions")
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String questionTitle;

    @Column(columnDefinition="TEXT")
    @Lob
    private String description;

    @Column(columnDefinition="TEXT")
    @Lob
    private String sourceCode;


    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;


    public Question() {
    }

    public Question(String questionTitle, String description, String sourceCode, Section section) {
        this.questionTitle = questionTitle;
        this.description = description;
        this.sourceCode = sourceCode;
        this.section = section;
    }


    public int getId() {
        return id;
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

    public Section getSection() {
        return section;
    }


    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
