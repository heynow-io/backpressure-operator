package io.heynow.operator.backpressure;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class BackpressureProvider {

    private BackpressureConfigService configService;
    private LoadingCache<String, BackpressureHandler> handlersCache;

    @Autowired
    public BackpressureProvider(BackpressureConfigService configService) {
        this.configService = configService;
        CacheLoader<String, BackpressureHandler> cacheLoader = new CacheLoader<String, BackpressureHandler>() {
            @Override
            public BackpressureHandler load(String eventType) throws Exception {
                return BackpressureFactory.createFromConfig(configService.getConfig(eventType));
            }
        };

        handlersCache = CacheBuilder.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build(cacheLoader);
    }

    public BackpressureHandler getHandler(String eventType) {
        return handlersCache.getUnchecked(eventType);
    }
}
