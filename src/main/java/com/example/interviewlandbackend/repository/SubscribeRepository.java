package com.example.interviewlandbackend.repository;

import com.example.interviewlandbackend.model.Subscribe;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeRepository extends JpaRepository<Subscribe , Integer> {


}
