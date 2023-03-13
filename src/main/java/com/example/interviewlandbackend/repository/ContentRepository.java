package com.example.interviewlandbackend.repository;

import com.example.interviewlandbackend.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content , Integer> {


    // https://stackoverflow.com/questions/18853452/sql-select-like-insensitive-casing
    @Query("SELECT c FROM Content c WHERE c.contentName LIKE %:search%")
    List<Content> searchForContentName(@Param("search") String search );

}
