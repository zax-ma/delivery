package com.study.delivery.services;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@Data
public class DistributionResult {
    private final Map<Long, Long> result;

    public DistributionResult(Map<Long, Long> result) {
        this.result = result;
    }
}
