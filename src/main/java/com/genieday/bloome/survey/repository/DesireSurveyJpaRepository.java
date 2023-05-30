package com.genieday.bloome.survey.repository;

import com.genieday.bloome.survey.DesireSurvey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesireSurveyJpaRepository extends JpaRepository<DesireSurvey, Long> {
    DesireSurvey findByUserId(Long id);
}
