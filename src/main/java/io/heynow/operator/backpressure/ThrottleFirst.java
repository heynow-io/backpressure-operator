package io.heynow.operator.backpressure;

import java.time.LocalDateTime;

public class ThrottleFirst implements BackpressureHandler {
    //in seconds
    private int timeWindowSize;
    private LocalDateTime nextFreeTimeSlot = LocalDateTime.MAX;

    public ThrottleFirst(int timeWindowSize) {
        this.timeWindowSize = timeWindowSize;
    }

    public boolean backpressure() {
        LocalDateTime currentTime = LocalDateTime.now();

        if (currentTime.compareTo(nextFreeTimeSlot) >= 0) {
            nextFreeTimeSlot = currentTime.plusSeconds(timeWindowSize);
            return true;
        }
        return false;
    }

}
