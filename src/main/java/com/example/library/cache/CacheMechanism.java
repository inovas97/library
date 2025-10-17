
package com.example.library.cache;
import jakarta.enterprise.context.ApplicationScoped;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;


@ApplicationScoped
public class CacheMechanism {
    private final Cache<String, Object> cache = Caffeine.newBuilder()
            .expireAfterWrite(1, TimeUnit.HOURS)
            .maximumSize(500)
            .build();

    public <T> T get(String key, Class<T> type) {
        Object value = cache.getIfPresent(key);
        return type.cast(value);
    }

    public void put(String key, Object value) {
        cache.put(key, value);
    }

    public void clear() {
        cache.invalidateAll();
    }
}