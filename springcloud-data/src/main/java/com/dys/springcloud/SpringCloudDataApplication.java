package com.dys.springcloud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dingyingsi
 */
@SpringBootApplication
public class SpringCloudDataApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}