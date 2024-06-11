package com.ljs.memorygame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MemoryGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemoryGameApplication.class, args);
    }

}
