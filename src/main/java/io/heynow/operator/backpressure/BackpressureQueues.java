package io.heynow.operator.backpressure;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface BackpressureQueues {
    String INPUT = "input";
    String INNER_INPUT = "in";
    String INNER_OUTPUT = "out";

    @Input(INPUT)
    SubscribableChannel notesIn();

    @Output(INNER_INPUT)
    MessageChannel innerInput();

    @Input(INNER_OUTPUT)
    SubscribableChannel innerOutput();
}
