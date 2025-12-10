package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application entrypoint.
 */
@SpringBootApplication
public class Application {
    private Application() {
        // prevent instantiation
    }

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

