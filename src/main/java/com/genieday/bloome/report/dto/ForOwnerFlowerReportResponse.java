package com.genieday.bloome.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForOwnerFlowerReportResponse {
    private ForOwnerFlowerReport window;
    private String comment;
}
