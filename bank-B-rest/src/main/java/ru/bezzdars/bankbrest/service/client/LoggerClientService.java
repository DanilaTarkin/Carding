package ru.bezzdars.bankbrest.service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.bezzdars.bankbrest.config.ServiceProperties;
import ru.bezzdars.bankbrest.enums.EndPoints;
import ru.bezzdars.bankbrest.model.RestMessage;
import ru.bezzdars.bankbrest.util.CardingUtils;

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
