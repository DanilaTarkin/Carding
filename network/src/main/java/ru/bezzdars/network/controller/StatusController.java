package ru.bezzdars.network.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bezzdars.network.model.RestMessage;
import ru.bezzdars.network.service.StatusService;

@RestController
@RequiredArgsConstructor
public class StatusController {
  private final StatusService service;

  @GetMapping("status")
  public RestMessage status() {
    return service.status();
  }
}
