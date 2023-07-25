package ru.bezzdars.bankarest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "service")
@Getter
@Setter
public class ServiceProperties {

    private String name;
    private String description;
    private String address;
  
    private String networkName;
    private String networkAddress;
    private String loggerName;
    private String loggerAddress;
}
