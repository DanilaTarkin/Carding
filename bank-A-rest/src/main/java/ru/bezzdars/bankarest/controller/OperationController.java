package ru.bezzdars.bankarest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.bezzdars.bankarest.model.RestMessage;
import ru.bezzdars.bankarest.service.OperationService;

@RestController
@RequestMapping("operation")
@RequiredArgsConstructor
public class OperationController {

  private final OperationService service;

  @GetMapping("get")
  public RestMessage get(@RequestParam Long cash, @RequestParam String from) {
    return service.get(cash, from);
  }

  @GetMapping("pay")
  public RestMessage pay(@RequestParam Long cash, @RequestParam String to) {
    return service.pay(cash, to);
  }
}
