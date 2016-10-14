package io.heynow.operator.backpressure;

import io.heynow.operator.backpressure.model.BackpressureConfig;

public interface BackpressureConfigService {
    BackpressureConfig getConfig(Long streamId);
}
