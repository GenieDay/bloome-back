package com.genieday.bloome.survey.service;

import com.genieday.bloome.garden.flower.Flower;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class SurveyService {
    private final SelfSurveyJpaRepository selfSurveyJpaRepository;
    private final DesireSurveyJpaRepository desireSurveyJpaRepository;
    private final OthersSurveyJpaRepository othersSurveyJpaRepository;
    private final UserService userService;

    public SurveyResponse self(SurveyRequest dto) {
        User user = userService.userExist(dto.getIdName());
        selfSurveyJpaRepository.save(
                SelfSurvey.builder()
                        .word1(dto.getWords().get(0))
                        .word2(dto.getWords().get(1))
                        .word3(dto.getWords().get(2))
                        .word4(dto.getWords().get(3))
                        .word5(dto.getWords().get(4))
                        .word6(dto.getWords().get(5))
                        .user(user)
                        .build()
        );
        return SurveyResponse.builder()
                .message("success.")
                .build();
    }

    public SurveyResponse desire(SurveyRequest dto) {
        User user = userService.userExist(dto.getIdName());
        desireSurveyJpaRepository.save(
                DesireSurvey.builder()
                        .word1(dto.getWords().get(0))
                        .word2(dto.getWords().get(1))
                        .word3(dto.getWords().get(2))
                        .word4(dto.getWords().get(3))
                        .word5(dto.getWords().get(4))
                        .word6(dto.getWords().get(5))
                        .user(user)
                        .build()
        );
        return SurveyResponse.builder()
                .message("success.")
                .build();
    }

    public SurveyResponse others(OthersSurveyRequest dto) {
        User user = userService.userExist(dto.getIdName());

        SelfSurvey selfSurvey = selfSurveyJpaRepository.findByUserId(user.getId());
        List<Integer> self = new ArrayList<>();
        self.add(selfSurvey.getWord1());
        self.add(selfSurvey.getWord2());
        self.add(selfSurvey.getWord3());
        self.add(selfSurvey.getWord4());
        self.add(selfSurvey.getWord5());
        self.add(selfSurvey.getWord6());

        List<Integer> others = dto.getWords();
        Set<Integer> set = new HashSet<>(self);

        int count = 0;
        for (Integer num : others) {
            if (set.contains(num)) {
                count++;
                set.remove(num);
            }
        }

        othersSurveyJpaRepository.save(
                OthersSurvey.builder()
                        .word1(dto.getWords().get(0))
                        .word2(dto.getWords().get(1))
                        .word3(dto.getWords().get(2))
                        .word4(dto.getWords().get(3))
                        .word5(dto.getWords().get(4))
                        .word6(dto.getWords().get(5))
                        .name(dto.getOthersName())
                        .user(user)
                        .leaves(count)
                        .comment(dto.getComment())
                        .build()
        );
        return SurveyResponse.builder()
                .message("success.")
                .build();
    }

    public List<Flower> getFlowers(User user) {
        List<OthersSurvey> othersSurveys = othersSurveyJpaRepository.findAllByUserId(user.getId());
        List<Flower> flowers = new ArrayList<>();
        for (OthersSurvey othersSurvey : othersSurveys) {
            flowers.add(
                    Flower.builder()
                            .flowerId(othersSurvey.getId())
                            .leaves(othersSurvey.getLeaves())
                            .testerName(othersSurvey.getName())
                            .build());
        }
        return flowers;
    }
}
