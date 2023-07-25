package ru.bezzdars.network.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum EndPoints {
  REG("/reg"),
  STATUS("/status"),
  LOGS("/logs"),
  GET("/get"),
  TRANSFER("/transfer"),
  SERVICES("/services"),
  SERVICES_SEARCH("/services/search");

  private final String route;
}
