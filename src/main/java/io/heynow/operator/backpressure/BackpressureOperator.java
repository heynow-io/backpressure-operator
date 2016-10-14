package io.heynow.operator.backpressure;

import io.heynow.stream.manager.client.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.Router;

@EnableBinding(Sink.class)
public class BackpressureOperator {
    private static final String INTERNAL = "internal";

    private Sink sink;
    private BackpressureProvider backpressureProvider;

    @Autowired
    public BackpressureOperator(Sink sink, BackpressureProvider backpressureProvider) {
        this.sink = sink;
        this.backpressureProvider = backpressureProvider;
    }

    @Filter(inputChannel = Sink.INPUT, outputChannel = INTERNAL)
    public boolean noteBackpressure(Note note) {
        return backpressureProvider.getHandler(note.getProcessingModel().getStreamId()).backpressure();
    }

    @Router(inputChannel = INTERNAL)
    public String router(Note note) {
        return note.proceed().getName();
    }
}
