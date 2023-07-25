package ru.bezzdars.bankarest.service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.bezzdars.bankarest.config.ServiceProperties;
import ru.bezzdars.bankarest.enums.EndPoints;
import ru.bezzdars.bankarest.model.RestMessage;
import ru.bezzdars.bankarest.util.CardingUtils;

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
