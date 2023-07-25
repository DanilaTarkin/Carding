package ru.bezzdars.cardingrest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bezzdars.cardingrest.model.RestMessage;
import ru.bezzdars.cardingrest.service.TransferService;

@RestController
@RequestMapping("transfer")
@RequiredArgsConstructor
public class TransferController {
  private final TransferService service;

  @GetMapping
  public RestMessage transfer(
      @RequestParam String from, @RequestParam String to, @RequestParam Long cash) {
    return service.transfer(from, to, cash);
  }
}
