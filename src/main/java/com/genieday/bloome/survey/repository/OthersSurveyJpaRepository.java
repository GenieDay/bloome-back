package com.genieday.bloome.survey.repository;

import com.genieday.bloome.garden.flower.Flower;
import com.genieday.bloome.survey.DesireSurvey;
import com.genieday.bloome.survey.OthersSurvey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OthersSurveyJpaRepository extends JpaRepository<OthersSurvey, Long> {
    List<OthersSurvey> findAllByUserId(Long userId);
}
