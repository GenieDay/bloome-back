package com.genieday.bloome.survey.controller;

import com.genieday.bloome.common.Result;
import com.genieday.bloome.survey.dto.OthersSurveyRequest;
import com.genieday.bloome.survey.dto.SurveyRequest;
import com.genieday.bloome.survey.dto.SurveyResponse;
import com.genieday.bloome.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/survey")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    /**
     * 내가 생각하는 나
     * @param surveyRequest
     * @return
     */
    @PostMapping("/self")
    public ResponseEntity<Result<SurveyResponse>> self(@RequestBody SurveyRequest surveyRequest){
        SurveyResponse surveyResponse = surveyService.self(surveyRequest);
        return ResponseEntity.ok().body(Result.success(surveyResponse));
    }

    /**
     * 내가 되고싶은 나
     * @param surveyRequest
     * @return
     */
    @PostMapping("/desire")
    public ResponseEntity<Result<SurveyResponse>> desire(@RequestBody SurveyRequest surveyRequest){
        SurveyResponse surveyResponse = surveyService.desire(surveyRequest);
        return ResponseEntity.ok().body(Result.success(surveyResponse));
    }

    /**
     * 남이 생각하는 나
     * @param othersSurveyRequest
     * @return
     */
    @PostMapping("/others")
    public ResponseEntity<Result<SurveyResponse>> desire(@RequestBody OthersSurveyRequest othersSurveyRequest){
        SurveyResponse surveyResponse = surveyService.others(othersSurveyRequest);
        return ResponseEntity.ok().body(Result.success(surveyResponse));
    }
}
