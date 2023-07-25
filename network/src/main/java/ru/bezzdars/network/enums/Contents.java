package ru.bezzdars.network.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Contents {
  APPLICATION_RUN_SUCCESS("Запуск прошел успешно"),
  APPLICATION_REG_SUCCESS("Регистрация в Сети прошла успешно"),
  APPLICATION_REG_FAILED("Регистрация в Сети провалилась"),
  APPLICATION_LOG_SUCCESS("Доступ к системе логирования разрешен"),
  APPLICATION_LOG_FAILED("Доступ к системе логирования запрещен"),

  SERVICES_GET_SUCCESS("Список микросервисов в сети:\n%s"),
  SERVICES_GET_BY_NAME_SUCCESS("Данные микросервиса:\n%s"),
  SERVICES_ADD_SUCCESS("Микросервис [%s] успешно зарегестрирован!");

  private final String description;
}
