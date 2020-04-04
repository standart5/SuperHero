package com.superhero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HeroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeroServiceApplication.class, args);
    }
}
