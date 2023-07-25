package ru.bezzdars.cardingrest.service.client;

import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.bezzdars.cardingrest.config.ServiceProperties;
import ru.bezzdars.cardingrest.enums.Contents;
import ru.bezzdars.cardingrest.enums.EndPoints;
import ru.bezzdars.cardingrest.model.RegistryData;
import ru.bezzdars.cardingrest.util.CardingUtils;

/** Сервис получения данных из сервиса Сети */
@Log4j2
@Service
@RequiredArgsConstructor
public class NetworkClientService {

  private final RestTemplate client;
  private final ServiceProperties properties;

  /**
   * Метод ищет адресс банка по имени в Сети
   *
   * @param name - название банка
   * @return адресс банка
   */
  public String getBankAddress(String name) {
    String addressBank = "UNKNOWN";
    String url =
        CardingUtils.getUrl(properties.getNetworkAddress(), EndPoints.SERVICES_SEARCH, "name=" + name);

    try {
      addressBank =
          Optional.ofNullable(client.getForEntity(url, RegistryData.class).getBody()).stream()
              .map(RegistryData::getAddress)
              .filter(Objects::nonNull)
              .findFirst()
              .orElse(addressBank);
    } catch (Exception e) {
      log.error(
          CardingUtils.getLog(
              Contents.TRANSACTION_FAILED, EndPoints.TRANSFER, properties.getName()));
    }

    return addressBank;
  }
}
