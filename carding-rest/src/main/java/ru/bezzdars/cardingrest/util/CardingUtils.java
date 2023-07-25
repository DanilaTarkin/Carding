package ru.bezzdars.cardingrest.util;

import static java.lang.String.format;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;
import ru.bezzdars.cardingrest.config.ServiceProperties;
import ru.bezzdars.cardingrest.enums.Contents;
import ru.bezzdars.cardingrest.enums.EndPoints;
import ru.bezzdars.cardingrest.enums.Levels;
import ru.bezzdars.cardingrest.model.RegistryData;
import ru.bezzdars.cardingrest.model.RestMessage;

@UtilityClass
public class CardingUtils {

  public static String getLog(Contents content, EndPoints endPoint, String name, Object... args) {
    return "API: http://"
        + name
        + endPoint.getRoute()
        + "\n"
        + format(content.getDescription(), args);
  }

  public static String getUrl(String address, EndPoints endPoint, String... params) {
    StringBuilder url = new StringBuilder("http://" + address + endPoint.getRoute());

    if (Objects.nonNull(params)) {
      url.append("?" + Stream.of(params).collect(Collectors.joining("&")));
    }

    return url.toString();
  }

  public static RestMessage getMessage(
      Contents content, Levels level, String serviceName, Object... contentArgs) {
    return RestMessage.builder()
        .service(serviceName)
        .content(format(content.getDescription(), contentArgs))
        .level(level)
        .time(LocalDateTime.now())
        .build();
  }

  public static RegistryData getRegistryData(ServiceProperties properties) {
    return RegistryData.builder()
        .name(properties.getName())
        .description(properties.getDescription())
        .address(properties.getAddress())
        .build();
  }
}
