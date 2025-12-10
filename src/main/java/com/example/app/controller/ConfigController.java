package com.example.app.controller;

import org.springframework.beans.factory.annotation.Value;  
// import org.springframework.beans.factory.annotation.Value;
import com.example.app.service.ConfigService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// @RestController
// @RequestMapping("/api/config")
// public class ConfigController {

//   private final ConfigService configService;

//   public ConfigController(ConfigService configService) {
//     this.configService = configService;
//   }

//   @GetMapping
//   public ResponseEntity<Map<String, String>> getConfig() {
//     return ResponseEntity.ok(
//       Map.of("message", configService.getMessage(), "mode", configService.getMode())
//     );
//   }
// }

@RestController
@RequestMapping("/api/config")
public class ConfigController {
  @Value("${app.message:hello}") private String message;
  @Value("${app.mode:dev}") private String mode;
  @GetMapping public Map<String,String> get() { return Map.of("message", message, "mode", mode); }
}
