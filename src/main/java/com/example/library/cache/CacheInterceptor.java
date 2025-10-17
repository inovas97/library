package com.example.library.cache;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Cacheable
@Interceptor
@Priority(Interceptor.Priority.APPLICATION + 10)
public class CacheInterceptor {

    @Inject
    CacheMechanism cacheMechanism;

    @AroundInvoke
    public Object around(InvocationContext ctx) throws Exception {
        Cacheable annotation = ctx.getMethod().getAnnotation(Cacheable.class);
        if (annotation == null) {
            annotation = ctx.getTarget().getClass().getAnnotation(Cacheable.class);
        }

        // Generate a cache key based on method + params
        String key = ctx.getMethod().getName() + "|" + java.util.Arrays.deepHashCode(ctx.getParameters());

        Object cachedValue = cacheMechanism.get(key, ctx.getMethod().getReturnType());
        if (cachedValue != null) {
            return cachedValue;
        }

        Object result = ctx.proceed();
        cacheMechanism.put(key, result);
        return result;
    }
}

