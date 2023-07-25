package ru.bezzdars.logger.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "service")
public class ServiceProperties {
  private String name;
  private String description;
  private String address;

  private String networkName;
  private String networkAddress;
}
