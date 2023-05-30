package com.genieday.bloome.survey.service;

import com.genieday.bloome.adjective.AdjectiveJpaRepository;
import com.genieday.bloome.garden.flower.Flower;
import com.genieday.bloome.report.dto.AdjectiveSet;
import com.genieday.bloome.report.dto.ForOwnerFlowerReport;
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
    private final AdjectiveJpaRepository adjectiveJpaRepository;
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

    public List<AdjectiveSet> adjectiveSets(Long flowerId) {
        OthersSurvey othersSurvey = othersSurveyJpaRepository.findById(flowerId).get();
        SelfSurvey selfSurvey = selfSurveyJpaRepository.findByUserId(othersSurvey.getUser().getId());

        List<Integer> self = new ArrayList<>();
        self.add(selfSurvey.getWord1());
        self.add(selfSurvey.getWord2());
        self.add(selfSurvey.getWord3());
        self.add(selfSurvey.getWord4());
        self.add(selfSurvey.getWord5());
        self.add(selfSurvey.getWord6());

        List<Integer> others = new ArrayList<>();
        others.add(othersSurvey.getWord1());
        others.add(othersSurvey.getWord2());
        others.add(othersSurvey.getWord3());
        others.add(othersSurvey.getWord4());
        others.add(othersSurvey.getWord5());
        others.add(othersSurvey.getWord6());

        List<AdjectiveSet> adjectiveSets = new ArrayList<>();
        for (Integer num : others) {
            if (self.contains(num)) {
                adjectiveSets.add(AdjectiveSet.builder()
                        .isMatch(Boolean.TRUE)
                        .word(adjectiveJpaRepository.findById(num).get().getWord())
                        .build());
            } else {
                adjectiveSets.add(AdjectiveSet.builder()
                        .isMatch(Boolean.FALSE)
                        .word(adjectiveJpaRepository.findById(num).get().getWord())
                        .build());
            }
        }
        return adjectiveSets;
    }

    public ForOwnerFlowerReport reportForOwner(Long flowerId) {
        OthersSurvey othersSurvey = othersSurveyJpaRepository.findById(flowerId).get();
        SelfSurvey selfSurvey = selfSurveyJpaRepository.findByUserId(othersSurvey.getUser().getId());

        List<Integer> self = new ArrayList<>();
        self.add(selfSurvey.getWord1());
        self.add(selfSurvey.getWord2());
        self.add(selfSurvey.getWord3());
        self.add(selfSurvey.getWord4());
        self.add(selfSurvey.getWord5());
        self.add(selfSurvey.getWord6());

        List<Integer> others = new ArrayList<>();
        others.add(othersSurvey.getWord1());
        others.add(othersSurvey.getWord2());
        others.add(othersSurvey.getWord3());
        others.add(othersSurvey.getWord4());
        others.add(othersSurvey.getWord5());
        others.add(othersSurvey.getWord6());

        List<String> open = new ArrayList<>();
        for (Integer num : others) {
            if (self.contains(num)) {
                open.add(adjectiveJpaRepository.findById(num).get().getWord());
            }
        }

        List<String> hidden = new ArrayList<>();
        for (Integer num : self) {
            if (!others.contains(num)) {
                hidden.add(adjectiveJpaRepository.findById(num).get().getWord());
            }
        }

        List<String> blind = new ArrayList<>();
        for (Integer num : others) {
            if (!self.contains(num)) {
                blind.add(adjectiveJpaRepository.findById(num).get().getWord());
            }
        }

        DesireSurvey desireSurvey = desireSurveyJpaRepository.findByUserId(othersSurvey.getUser().getId());
        List<String> unknown = new ArrayList<>();
        unknown.add(adjectiveJpaRepository.findById(desireSurvey.getWord1()).get().getWord());
        unknown.add(adjectiveJpaRepository.findById(desireSurvey.getWord2()).get().getWord());
        unknown.add(adjectiveJpaRepository.findById(desireSurvey.getWord3()).get().getWord());
        unknown.add(adjectiveJpaRepository.findById(desireSurvey.getWord4()).get().getWord());
        unknown.add(adjectiveJpaRepository.findById(desireSurvey.getWord5()).get().getWord());
        unknown.add(adjectiveJpaRepository.findById(desireSurvey.getWord6()).get().getWord());

        return ForOwnerFlowerReport.builder()
                .unknown(unknown)
                .open(open)
                .hidden(hidden)
                .blind(blind)
                .build();
    }
}
