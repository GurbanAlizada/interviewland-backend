package com.example.interviewlandbackend.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "subscribes_user")
public class Subscribe implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;


    public Subscribe() {
    }

    public Subscribe( String email) {
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

}
