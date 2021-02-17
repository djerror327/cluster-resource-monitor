package com.dinusha.soft;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStart {
    private static final Logger logger = Logger.getLogger(AppStart.class);

    public static void main(String[] args) {
        logger.info("Stating Cluster Resource Monitoring APP...");
        SpringApplication.run(AppStart.class, args);
    }
}
