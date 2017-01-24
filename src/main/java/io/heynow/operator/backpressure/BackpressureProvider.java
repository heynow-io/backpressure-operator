package io.heynow.operator.backpressure;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


public class BackpressureProvider {

    private BackpressureConfigService configService;
    private LoadingCache<Long, BackpressureHandler> handlersCache;


    public BackpressureProvider(BackpressureConfigService configService) {
        this.configService = configService;
        CacheLoader<Long, BackpressureHandler> cacheLoader = new CacheLoader<Long, BackpressureHandler>() {
            @Override
            public BackpressureHandler load(Long streamId) throws Exception {
                return BackpressureFactory.createFromConfig(configService.getConfig(streamId));
            }
        };

        handlersCache = CacheBuilder.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build(cacheLoader);
    }

    public BackpressureHandler getHandler(Long streamId) {
        return handlersCache.getUnchecked(streamId);
    }
}
