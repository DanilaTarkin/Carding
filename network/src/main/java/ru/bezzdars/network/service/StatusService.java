package ru.bezzdars.network.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.bezzdars.network.config.ServiceProperties;
import ru.bezzdars.network.enums.*;
import ru.bezzdars.network.model.RegistryData;
import ru.bezzdars.network.model.RestMessage;
import ru.bezzdars.network.util.CardingUtils;

@Log4j2
@Service
@RequiredArgsConstructor
public class StatusService {

  private final ServiceProperties properties;

  List<RegistryData> services = new ArrayList<>();

  public RestMessage status() {
    var message =
        CardingUtils.getMessage(
            Contents.APPLICATION_RUN_SUCCESS, Levels.INFO, properties.getName());

    log.info(
        CardingUtils.getLog(
            Contents.APPLICATION_RUN_SUCCESS, EndPoints.STATUS, properties.getName()));
    return message;
  }
}
