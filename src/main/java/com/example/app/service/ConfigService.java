package com.example.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {
  @Value("${app.message:hello}")
  private String message;

  @Value("${app.mode:dev}")
  private String mode;

  public String getMessage() { return message; }
  public String getMode() { return mode; }
}
