package ru.bezzdars.logger.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bezzdars.logger.model.RestMessage;
import ru.bezzdars.logger.service.StatusService;

@RestController
@RequestMapping("status")
@RequiredArgsConstructor
public class StatusController {
  private final StatusService service;

  @GetMapping
  public RestMessage status() {
    return service.status();
  }
}
