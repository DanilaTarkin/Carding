package ru.bezzdars.bankarest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.bezzdars.bankarest.config.ServiceProperties;
import ru.bezzdars.bankarest.enums.Contents;
import ru.bezzdars.bankarest.enums.EndPoints;
import ru.bezzdars.bankarest.enums.Levels;
import ru.bezzdars.bankarest.model.RestMessage;
import ru.bezzdars.bankarest.util.CardingUtils;

@Log4j2
@Service
@RequiredArgsConstructor
public class StatusService {

  private final ServiceProperties properties;

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
