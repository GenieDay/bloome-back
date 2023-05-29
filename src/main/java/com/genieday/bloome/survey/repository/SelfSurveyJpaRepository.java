package com.genieday.bloome.survey.repository;

import com.genieday.bloome.survey.SelfSurvey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelfSurveyJpaRepository extends JpaRepository<SelfSurvey, Long> {
    SelfSurvey findByUserId(Long id);
}
