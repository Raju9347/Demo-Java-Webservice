package com.example.app.controller;

import com.example.app.service.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// @RestController
// @RequestMapping("/api/items")
// public class ItemController {

//   private final StorageService storageService;

//   public ItemController(StorageService storageService) {
//     this.storageService = storageService;
//   }

//   @GetMapping
//   public ResponseEntity<List<Map<String, Object>>> list() {
//     try {
//       return ResponseEntity.ok(storageService.listItems());
//     } catch (Exception e) {
//       return ResponseEntity.internalServerError().build();
//     }
//   }

//   @PostMapping
//   public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> item) {
//     try {
//       storageService.addItem(item);
//       return ResponseEntity.ok(item);
//     } catch (Exception e) {
//       return ResponseEntity.internalServerError().build();
//     }
//   }
// }

@RestController
@RequestMapping("/api/items")
public class ItemController {
  private final StorageService storage;
  public ItemController(StorageService storage) { this.storage = storage; }

  @GetMapping
  public ResponseEntity<List<Map<String,Object>>> list() {
    try { return ResponseEntity.ok(storage.listItems()); }
    catch (Exception e) { return ResponseEntity.internalServerError().build(); }
  }

  @PostMapping
  public ResponseEntity<Map<String,Object>> create(@RequestBody Map<String,Object> item) {
    try { storage.addItem(item); return ResponseEntity.ok(item); }
    catch (Exception e) { return ResponseEntity.internalServerError().build(); }
  }
}

