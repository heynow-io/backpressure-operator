package io.heynow.operator.backpressure;

import feign.Param;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(
        name = "stream-manager",
        url = "${stream-manager.url:}"
)
public interface StreamManagerClient {

    @RequestMapping(value = "/operators/{eventType}/properties", method = GET)
    Map<String, Object> getConfig(@Param("eventType") String eventType);
}
