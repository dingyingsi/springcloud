package com.dys.spring.cache.service;

import com.dys.spring.cache.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CacheService {

    @Cacheable(cacheManager = "users")
    public List<User> users() {
        List<User> users = new ArrayList<User>();
        users.add(new User(1L, "ding", 20, new Date()));
        users.add(new User(2L, "ying", 21, new Date()));
        users.add(new User(3L, "si", 22, new Date()));

        return users;
    }

    @Cacheable(cacheManager = "user")
    public User user() {
        return new User(4L, "dingyingsi", 23, new Date());
    }

}
