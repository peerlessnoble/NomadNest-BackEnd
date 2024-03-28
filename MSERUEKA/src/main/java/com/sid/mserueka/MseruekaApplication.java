package com.sid.mserueka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MseruekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MseruekaApplication.class, args);
    }

}
