package com.example.app.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StorageService {
  @Value("${app.itemsFile:/data/items.json}")
  private String itemsFile;
  private final ObjectMapper mapper = new ObjectMapper();

  public List<Map<String,Object>> listItems() throws Exception {
    var f = Path.of(itemsFile).toFile();
    if (!f.exists()) return new ArrayList<>();
    var bytes = Files.readAllBytes(f.toPath());
    return bytes.length == 0 ? new ArrayList<>() :
      mapper.readValue(bytes, new TypeReference<List<Map<String,Object>>>(){});
  }

  public void addItem(Map<String,Object> item) throws Exception {
    var items = listItems();
    items.add(item);
    var f = Path.of(itemsFile).toFile();
    f.getParentFile().mkdirs();
    mapper.writerWithDefaultPrettyPrinter().writeValue(f, items);
  }
}

