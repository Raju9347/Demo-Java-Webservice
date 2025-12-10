package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application entrypoint.
 */
@SpringBootApplication
public final class Application {

    private Application() {
        // prevent instantiation
    }

    /**
     * Starts the Spring Boot application.
     * @param args CLI args
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
