package ru.bezzdars.cardingrest.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum EndPoints {
  STATUS("/status"),
  LOGS("/logs"),
  OPERATION_GET("/operation/get"),
  OPERATION_PAY("/operation/pay"),
  TRANSFER("/transfer"),
  SERVICES("/services"),
  SERVICES_SEARCH("/services/search");

  private final String route;
}
