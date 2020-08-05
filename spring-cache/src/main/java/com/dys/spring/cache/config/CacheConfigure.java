package com.dys.spring.cache.config;

import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class CacheConfigure extends CachingConfigurerSupport {

    @Bean
    CacheManager cacheManager(RedissonClient redissonClient) {
        Map<String, CacheConfig> config = new HashMap<>(16);
        config.put("user", new CacheConfig(60 * 1000, 12 * 60 * 1000));
        config.put("users", new CacheConfig(60 * 1000, 12 * 60 * 1000));
        return new RedissonSpringCacheManager(redissonClient, config);

    }

    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append("CacheKey:");
                sb.append(target.getClass().getSimpleName());
                sb.append('.').append(method.getName()).append(":");

                // 这里需要注意，参数太多的话考虑使用其他拼接方式
                for (Object obj : params) {
                    if (obj != null) {
                        sb.append(obj.toString());
                        sb.append(".");
                    }
                }
                return sb.toString();
            }
        };
    }
}
