package com.genieday.bloome.garden.service;

import com.genieday.bloome.garden.flower.Flower;
import com.genieday.bloome.garden.flower.FlowersResponse;
import com.genieday.bloome.survey.service.SurveyService;
import com.genieday.bloome.user.User;
import com.genieday.bloome.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GardenService {

    private final UserService userService;
    private final SurveyService surveyService;

    public FlowersResponse getFlowers(String idName) {
        User user = userService.userExist(idName);
        List<Flower> flowers = surveyService.getFlowers(user);
        return FlowersResponse.builder()
                .flowers(flowers)
                .build();
    }
}
