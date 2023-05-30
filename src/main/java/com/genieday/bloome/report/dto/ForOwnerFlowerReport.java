package com.genieday.bloome.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForOwnerFlowerReport {
    private List<String> open;
    private List<String> blind;
    private List<String> hidden;
    private List<String> unknown;

}
