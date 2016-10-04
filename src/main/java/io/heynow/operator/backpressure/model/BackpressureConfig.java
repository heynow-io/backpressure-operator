package io.heynow.operator.backpressure.model;

import lombok.Data;

import java.util.Map;

@Data
public class BackpressureConfig {
    private final String backpressureType;
    private final Map<String, String> properties;
}
