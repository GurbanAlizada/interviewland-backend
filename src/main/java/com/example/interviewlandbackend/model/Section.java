package com.example.interviewlandbackend.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "sections")
public class Section implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String sectionName;


    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;


    @OneToMany(mappedBy = "section" ,
            cascade = {
            CascadeType.MERGE , CascadeType.PERSIST , CascadeType.REMOVE
    }
    )
    private List<Question> questionList;


    public Section() {
    }


    public Section( String sectionName, Content content) {
        this.sectionName = sectionName;
        this.content = content;
    }



    public int getId() {
        return id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public Content getContent() {
        return content;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
