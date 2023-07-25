package ru.bezzdars.cardingrest.enums;

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

  BANK_RESPONSE_FAILED("Банк %s не смог ответить на запрос"),

  TRANSACTION_SUCCESS("Транзакция выполнена успешно!"),
  TRANSACTION_FAILED("Во время проведения транзакции произошли ошибки. Операция отменена");

  private final String description;
}
