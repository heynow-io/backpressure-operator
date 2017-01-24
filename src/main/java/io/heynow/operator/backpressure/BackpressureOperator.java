package io.heynow.operator.backpressure;

import io.heynow.stream.manager.client.model.Note;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

@Slf4j
@EnableBinding(BackpressureQueues.class)
public class BackpressureOperator {

    private MessageChannel innerInput;

    @Autowired
    public BackpressureOperator( @Qualifier(BackpressureQueues.INNER_INPUT) MessageChannel innerInput) {
        this.innerInput = innerInput;
    }

    @StreamListener(BackpressureQueues.INPUT)
    public void handleNote(Note note) {
        log.info(note.toString());
        innerInput.send(MessageBuilder.withPayload(note).build());
    }

    @Router(inputChannel = BackpressureQueues.INNER_OUTPUT)
    public String router(Note note) {
        return note.proceed().getName();
    }
}
