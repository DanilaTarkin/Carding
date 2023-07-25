package ru.bezzdars.cardingrest.service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.bezzdars.cardingrest.config.ServiceProperties;
import ru.bezzdars.cardingrest.enums.EndPoints;
import ru.bezzdars.cardingrest.model.RestMessage;
import ru.bezzdars.cardingrest.util.CardingUtils;

/** Сервис взаимодействия с сервисом логирования */
@Service
@RequiredArgsConstructor
public class LoggerClientService {

  private final RestTemplate client;
  private final ServiceProperties properties;

  /**
   * Метод отправки сообщения в сервис логирования
   *
   * @param name - название банка
   * @return адресс банка
   */
  public void send(RestMessage message) {
    String url = CardingUtils.getUrl(properties.getLoggerAddress(), EndPoints.LOGS);

    try {
      client.postForEntity(url, message, message.getClass());
    } catch (Exception e) {
      /* Игнорируем */
    }
  }
}
