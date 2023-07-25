package ru.bezzdars.logger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.bezzdars.logger.config.ServiceProperties;
import ru.bezzdars.logger.enums.Contents;
import ru.bezzdars.logger.enums.EndPoints;
import ru.bezzdars.logger.enums.Levels;
import ru.bezzdars.logger.model.RestMessage;
import ru.bezzdars.logger.util.CardingUtils;

@Log4j2
@Service
@RequiredArgsConstructor
public class LogService {
  private final ServiceProperties properties;

  private List<RestMessage> logs =
      new ArrayList<>(
          List.of(
              CardingUtils.getMessage(Contents.APPLICATION_RUN_SUCCESS, Levels.INFO, "logger")));

  public List<RestMessage> getAll() {
    var logsSortedByTime =
        logs.stream()
            .sorted((RestMessage m1, RestMessage m2) -> m2.getTime().compareTo(m1.getTime()))
            .collect(Collectors.toList());
    var logsText =
        logsSortedByTime.stream().map(RestMessage::toString).collect(Collectors.joining("\n"));
    log.info(
        CardingUtils.getLog(
            Contents.LOGS_GET_ALL_SUCCESS, EndPoints.LOGS, properties.getName(), logsText));
    return logsSortedByTime;
  }

  public RestMessage add(RestMessage message) {
    logs.add(message);
    log.info(
        CardingUtils.getLog(
            Contents.LOGS_ADD_SUCCESS, EndPoints.LOGS, properties.getName(), message.toString()));
    return CardingUtils.getMessage(
        Contents.LOGS_ADD_SUCCESS, Levels.INFO, properties.getName(), message.toString());
  }
}
