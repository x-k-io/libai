package com.kite.libai.provider.common.service;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class SegmentIdentifierGenerator implements IdentifierGenerator {

    private final SegmentIdGeneratorService segmentIdGeneratorService;

    public SegmentIdentifierGenerator(@Lazy SegmentIdGeneratorService segmentIdGeneratorService) {
        this.segmentIdGeneratorService = segmentIdGeneratorService;
    }

    @Override
    public Number nextId(Object entity) {
        return segmentIdGeneratorService.genUniqueId();
    }
}
