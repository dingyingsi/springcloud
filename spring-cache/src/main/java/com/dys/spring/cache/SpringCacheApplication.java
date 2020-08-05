package com.dys.spring.cache;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author dingyingsi
 */
@SpringBootApplication
@EnableCaching
public class SpringCacheApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringCacheApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

    }
}
