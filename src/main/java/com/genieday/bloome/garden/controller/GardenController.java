package com.genieday.bloome.garden.controller;

import com.genieday.bloome.common.Result;
import com.genieday.bloome.garden.flower.FlowersResponse;
import com.genieday.bloome.garden.service.GardenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/garden")
@RequiredArgsConstructor
public class GardenController {

    private final GardenService gardenService;

    @GetMapping("{idName}")
    public ResponseEntity<Result<FlowersResponse>> getFlowers(@PathVariable String idName){
        FlowersResponse flowerResponse = gardenService.getFlowers(idName);
        return ResponseEntity.ok().body(Result.success(flowerResponse));
    }
}
