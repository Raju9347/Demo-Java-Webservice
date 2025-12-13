package com.example.app.controller;

import org.springframework.beans.factory.annotation.Value;  
// import org.springframework.beans.factory.annotation.Value;
import com.example.app.service.ConfigService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    @Value("${app.message:hello}")
    private String message;

    @Value("${app.mode:dev}")
    private String mode;

    // Existing endpoint: returns config values
    @GetMapping
    public Map<String, String> get() {
        return Map.of("message", message, "mode", mode);
    }

    // New endpoint: burns CPU for HPA testing
    @GetMapping("/stress")
    public String stress() {
        long sum = 0;
        for (int i = 0; i < 100000000; i++) {
            sum += i;
        }
        return "CPU load done: " + sum;
    }
}
