package io.heynow.operator.backpressure;

import io.heynow.stream.manager.client.EnableStreamManagerClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableStreamManagerClient
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
