package io.heynow.operator.backpressure;

import io.heynow.operator.backpressure.model.BackpressureConfig;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BackpressureFactory {
    public static BackpressureHandler createFromConfig(BackpressureConfig config) {
        return new ThrottleFirst(10); //TODO: finish implementation
    }
}
