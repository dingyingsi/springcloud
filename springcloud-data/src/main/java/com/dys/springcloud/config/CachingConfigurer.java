package com.dys.springcloud.config;

import org.redisson.Redisson;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class CachingConfigurer {

    @Resource
    private Redisson redisson;

    @Bean
    public CacheManager cacheManager() {
        RedissonSpringCacheManager redissonSpringCacheManager = new RedissonSpringCacheManager(redisson);

        return redissonSpringCacheManager;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        KeyGenerator keyGenerator = (o, method, params) -> {
            StringBuilder key = new StringBuilder();
            key.append("word:");
            key.append(o.getClass().getName());
            key.append(":");
            key.append(method.getName());
            key.append(":");

            for (Object param : params) {
                if (param != null) {
                    key.append(param.toString());
                    key.append(".");
                }
            }

            return key.toString();
        };

        return keyGenerator;
    }
}
