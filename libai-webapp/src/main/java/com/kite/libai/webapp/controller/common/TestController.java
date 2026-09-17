package com.kite.libai.webapp.controller.common;

import com.kite.libai.provider.common.service.SegmentIdGeneratorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/app/v1/test")
public class TestController {

    private final SegmentIdGeneratorService segmentIdGenerator;

    @GetMapping("/nexId")
    public Long nexId() {
        return segmentIdGenerator.genOrderNo();
    }
}
