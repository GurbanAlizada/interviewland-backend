package com.example.interviewlandbackend.repository;

import com.example.interviewlandbackend.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section , Integer> {

    List<Section> getAllByContent_Id(int contentId);

}
