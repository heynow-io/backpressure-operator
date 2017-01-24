package io.heynow.operator.backpressure;

import io.heynow.operator.backpressure.model.BackpressureConfig;
import io.heynow.stream.manager.client.facade.StreamManagerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


public class BackpressureConfigServiceImpl implements BackpressureConfigService {

    private final StreamManagerClient streamManagerClient;


    public BackpressureConfigServiceImpl(StreamManagerClient streamManagerClient) {
        this.streamManagerClient = streamManagerClient;
    }


    @Override
    public BackpressureConfig getConfig(Long streamId) {
        Map<String, Object> properties = streamManagerClient.getProperties(streamId);
        String type = (String) properties.getOrDefault("backpressureType", "THROTTLE_FIRST");
        return new BackpressureConfig(type, null); //TODO: get backpressure props
    }
}
