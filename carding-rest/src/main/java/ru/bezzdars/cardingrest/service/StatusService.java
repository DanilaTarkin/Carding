package ru.bezzdars.cardingrest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.bezzdars.cardingrest.config.ServiceProperties;
import ru.bezzdars.cardingrest.enums.Contents;
import ru.bezzdars.cardingrest.enums.EndPoints;
import ru.bezzdars.cardingrest.enums.Levels;
import ru.bezzdars.cardingrest.model.RestMessage;
import ru.bezzdars.cardingrest.util.CardingUtils;

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
