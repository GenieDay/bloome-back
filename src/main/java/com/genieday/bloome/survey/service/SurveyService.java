package com.genieday.bloome.survey.service;

import com.genieday.bloome.survey.DesireSurvey;
import com.genieday.bloome.survey.OthersSurvey;
import com.genieday.bloome.survey.SelfSurvey;
import com.genieday.bloome.survey.dto.OthersSurveyRequest;
import com.genieday.bloome.survey.dto.SurveyRequest;
import com.genieday.bloome.survey.dto.SurveyResponse;
import com.genieday.bloome.survey.repository.DesireSurveyJpaRepository;
import com.genieday.bloome.survey.repository.OthersSurveyJpaRepository;
import com.genieday.bloome.survey.repository.SelfSurveyJpaRepository;
import com.genieday.bloome.user.User;
import com.genieday.bloome.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SurveyService {
    private final SelfSurveyJpaRepository selfSurveyJpaRepository;
    private final DesireSurveyJpaRepository desireSurveyJpaRepository;
    private final OthersSurveyJpaRepository othersSurveyJpaRepository;
    private final UserService userService;

    public SurveyResponse self(SurveyRequest dto) {
        userService.idNameExist(dto.getIdName());
        User user = userService.userExist(dto.getIdName());
        selfSurveyJpaRepository.save(
                SelfSurvey.builder()
                        .word1(dto.getWords().get(1))
                        .word2(dto.getWords().get(2))
                        .word3(dto.getWords().get(3))
                        .word4(dto.getWords().get(4))
                        .word5(dto.getWords().get(5))
                        .word6(dto.getWords().get(6))
                        .user(user)
                        .build()
        );
        return SurveyResponse.builder()
                .message("success.")
                .build();
    }

    public SurveyResponse desire(SurveyRequest dto) {
        userService.idNameExist(dto.getIdName());
        User user = userService.userExist(dto.getIdName());
        desireSurveyJpaRepository.save(
                DesireSurvey.builder()
                        .word1(dto.getWords().get(1))
                        .word2(dto.getWords().get(2))
                        .word3(dto.getWords().get(3))
                        .word4(dto.getWords().get(4))
                        .word5(dto.getWords().get(5))
                        .word6(dto.getWords().get(6))
                        .user(user)
                        .build()
        );
        return SurveyResponse.builder()
                .message("success.")
                .build();
    }

    public SurveyResponse others(OthersSurveyRequest dto) {
        userService.idNameExist(dto.getIdName());
        User user = userService.userExist(dto.getIdName());
        othersSurveyJpaRepository.save(
                OthersSurvey.builder()
                        .word1(dto.getWords().get(1))
                        .word2(dto.getWords().get(2))
                        .word3(dto.getWords().get(3))
                        .word4(dto.getWords().get(4))
                        .word5(dto.getWords().get(5))
                        .word6(dto.getWords().get(6))
                        .name(dto.getOthersName())
                        .user(user)
                        .build()
        );
        return SurveyResponse.builder()
                .message("success.")
                .build();
    }
}
