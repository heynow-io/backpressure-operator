package io.heynow.operator.backpressure;

import io.heynow.operator.backpressure.model.BackpressureConfig;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StreamManagerClientFacade  implements BackpressureConfigService{

    private StreamManagerClient client;

    @Autowired
    public StreamManagerClientFacade(StreamManagerClient client) {
        this.client = client;
    }

    @Override
    public BackpressureConfig getConfig(String eventType) {
        Map<String, Object> config = client.getConfig(eventType);

        return readConfig(config);
    }

    private BackpressureConfig readConfig(Map<String , Object> rawConfig) {
        return new BackpressureConfig("todo", new HashMap<String, String>());
    }
}
