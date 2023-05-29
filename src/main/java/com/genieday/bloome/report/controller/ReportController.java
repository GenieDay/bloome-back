package com.genieday.bloome.report.controller;

import com.genieday.bloome.common.Result;
import com.genieday.bloome.garden.flower.FlowersResponse;
import com.genieday.bloome.report.dto.ForVisitorsFlowerReport;
import com.genieday.bloome.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    /*@GetMapping("/for-owner/{flowerId}")
    public ResponseEntity<Result<FlowersResponse>> getFlowerByOwner(@PathVariable String flowerId){
        FlowersResponse flowerResponse = gardenService.getFlowers(idName, authentication.getName().equals(idName));
        return ResponseEntity.ok().body(Result.success(flowerResponse));
    }*/

    @GetMapping("/for-visitors/{flowerId}")
    public ResponseEntity<Result<ForVisitorsFlowerReport>> getFlowerByVisitor(@PathVariable Long flowerId){
        ForVisitorsFlowerReport flowerReport = reportService.getFlowerByVisitor(flowerId);
        return ResponseEntity.ok().body(Result.success(flowerReport));
    }
}
