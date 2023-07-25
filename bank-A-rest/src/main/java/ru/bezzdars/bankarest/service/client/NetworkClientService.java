package ru.bezzdars.bankarest.service.client;

import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.bezzdars.bankarest.config.ServiceProperties;
import ru.bezzdars.bankarest.enums.EndPoints;
import ru.bezzdars.bankarest.model.RegistryData;
import ru.bezzdars.bankarest.util.CardingUtils;

/** Сервис взаимодействия с сервисом Сети */
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
      /* Игнорируем */
    }

    return addressBank;
  }
}
