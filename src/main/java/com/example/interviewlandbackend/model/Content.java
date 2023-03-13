package com.example.interviewlandbackend.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "contents")
public class Content implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String contentName;

    @OneToMany(
            mappedBy = "content" ,
            cascade = {
                    CascadeType.MERGE , CascadeType.PERSIST , CascadeType.REMOVE
            }
    )
    private List<Section> sectionList;


    public Content() {
    }

    public Content(String contentName) {
        this.contentName = contentName;
    }


    public int getId() {
        return id;
    }

    public String getContentName() {
        return contentName;
    }

    public List<Section> getSectionList() {
        return sectionList;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }
}
