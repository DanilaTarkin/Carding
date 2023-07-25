package ru.bezzdars.logger.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bezzdars.logger.model.RestMessage;
import ru.bezzdars.logger.service.LogService;

@RestController
@RequestMapping("logs")
@RequiredArgsConstructor
public class LogController {
  private final LogService service;

  @GetMapping
  public List<RestMessage> getAll() {
    return service.getAll();
  }

  @PostMapping
  public RestMessage add(@RequestBody RestMessage logService) {
    return service.add(logService);
  }
}
