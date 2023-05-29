package com.genieday.bloome.survey.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OthersSurveyRequest {
    private String idName;
    private String othersName;
    private List<Integer> words;
}
