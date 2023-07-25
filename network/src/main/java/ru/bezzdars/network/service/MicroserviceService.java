package ru.bezzdars.network.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.bezzdars.network.config.ServiceProperties;
import ru.bezzdars.network.enums.*;
import ru.bezzdars.network.model.RegistryData;
import ru.bezzdars.network.model.RestMessage;
import ru.bezzdars.network.util.CardingUtils;

@Log4j2
@Service
@RequiredArgsConstructor
public class MicroserviceService {

  private final RestTemplate client;
  private final ServiceProperties properties;

  List<RegistryData> services = new ArrayList<>();

  public List<RegistryData> get() {
    var serviceNamesLog =
        services.stream().map(RegistryData::getName).collect(Collectors.joining("\n"));

    log.info(
        CardingUtils.getLog(
            Contents.SERVICES_GET_SUCCESS,
            EndPoints.SERVICES,
            properties.getName(),
            serviceNamesLog));
    return services;
  }

  public RegistryData getByName(String name) {
    var serviceByName =
        services.stream()
            .filter(service -> service.getName().equals(name))
            .findFirst()
            .orElse(RegistryData.builder().build());

    log.info(
        CardingUtils.getLog(
            Contents.SERVICES_GET_BY_NAME_SUCCESS,
            EndPoints.SERVICES_SEARCH,
            properties.getName(),
            serviceByName.toString()));
    return serviceByName;
  }

  public RestMessage add(RegistryData registry) {
    var message =
        CardingUtils.getMessage(
            Contents.SERVICES_ADD_SUCCESS, Levels.INFO, properties.getName(), registry.getName());

    services.add(registry);

    try {
      var url = CardingUtils.getUrl(properties.getLoggerAddress(), EndPoints.LOGS);
      client.postForEntity(url, message, message.getClass());
    } catch (Exception e) {
      /* empty */
    }

    log.info(
        CardingUtils.getLog(
            Contents.SERVICES_ADD_SUCCESS,
            EndPoints.SERVICES,
            properties.getName(),
            registry.getName()));
    return message;
  }
}
