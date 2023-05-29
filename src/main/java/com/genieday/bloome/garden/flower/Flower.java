package com.genieday.bloome.garden.flower;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flower {
    private String testerName;
    private Long flowerId;
    private Integer leaves;
}
