package ru.bezzdars.logger.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Contents {
  APPLICATION_RUN_SUCCESS("Запуск прошел успешно"),
  APPLICATION_REG_SUCCESS("Регистрация в Сети прошла успешно"),
  APPLICATION_REG_FAILED("Регистрация в Сети провалилась"),

  LOGS_GET_ALL_SUCCESS("Список логов:\n%s"),
  LOGS_ADD_SUCCESS("Лог успешно добавлен:\n%s");

  private final String description;
}
