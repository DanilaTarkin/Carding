package ru.bezzdars.bankbrest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bezzdars.bankbrest.model.RestMessage;
import ru.bezzdars.bankbrest.service.StatusService;

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
