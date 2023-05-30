package com.genieday.bloome.report.service;

import com.genieday.bloome.report.dto.ForOwnerFlowerReport;
import com.genieday.bloome.report.dto.ForOwnerFlowerReportResponse;
import com.genieday.bloome.report.dto.ForVisitorsFlowerReport;
import com.genieday.bloome.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final SurveyService surveyService;
    public ForVisitorsFlowerReport getFlowerByVisitor(Long flowerId) {

        return ForVisitorsFlowerReport.builder()
                .adjectiveSets(surveyService.adjectiveSets(flowerId))
                .build();
    }

    public ForOwnerFlowerReportResponse getFlowerbyOwner(Long flowerId) {
        ForOwnerFlowerReport flowerReport = surveyService.reportForOwner(flowerId);
        return ForOwnerFlowerReportResponse.builder()
                .window(flowerReport)
                .comment(surveyService.getComment(flowerId))
                .build();
    }
}
