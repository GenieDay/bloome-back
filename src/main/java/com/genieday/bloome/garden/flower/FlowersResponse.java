package com.genieday.bloome.garden.flower;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlowersResponse {
    private Boolean owner;
    private String name;
    private List<Flower> flowers;
}
