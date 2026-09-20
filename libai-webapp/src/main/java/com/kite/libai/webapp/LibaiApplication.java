package com.kite.libai.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.kite.libai")
public class LibaiApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibaiApplication.class, args);
    }
}
