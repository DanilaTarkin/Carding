package ru.bezzdars.cardingrest.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.bezzdars.cardingrest.enums.Contents;
import ru.bezzdars.cardingrest.enums.EndPoints;
import ru.bezzdars.cardingrest.enums.Levels;
import ru.bezzdars.cardingrest.util.CardingUtils;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class ClientConfiguration {

  private final ServiceProperties properties;

  @Bean
  public RestTemplate restTemplate() {
    return registryInNetwork(new RestTemplate());
  }

  private RestTemplate registryInNetwork(RestTemplate client) {
    registrationInNetwork(client);
    return client;
  }

  private void registrationInNetwork(RestTemplate client) {
    try {
      var url = CardingUtils.getUrl(properties.getNetworkAddress(), EndPoints.SERVICES);
      var registryData = CardingUtils.getRegistryData(properties);

      client.postForEntity(url, registryData, registryData.getClass());

      log.info(
          CardingUtils.getLog(
              Contents.APPLICATION_REG_SUCCESS, EndPoints.SERVICES, properties.getNetworkName()));
    } catch (Exception e) {
      log.error(
          CardingUtils.getLog(
              Contents.APPLICATION_REG_FAILED, EndPoints.SERVICES, properties.getNetworkName()));
    }
  }
}
