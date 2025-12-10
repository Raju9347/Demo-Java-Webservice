package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application entrypoint.
 */
@SpringBootApplication
public class Application {

    // Default constructor must be visible (public or package-private)
    public Application() {
        // empty constructor
    }
    @param args CLI arguments

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}


