package ru.bezzdars.network.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bezzdars.network.model.RegistryData;
import ru.bezzdars.network.model.RestMessage;
import ru.bezzdars.network.service.MicroserviceService;

@RestController
@RequestMapping("services")
@RequiredArgsConstructor
public class MicroserviceController {
  private final MicroserviceService service;

  @GetMapping
  public List<RegistryData> get() {
    return service.get();
  }

  @GetMapping("search")
  public RegistryData getByName(@RequestParam String name) {
    return service.getByName(name);
  }

  @PostMapping
  public RestMessage add(@RequestBody RegistryData registry) {
    return service.add(registry);
  }
}
