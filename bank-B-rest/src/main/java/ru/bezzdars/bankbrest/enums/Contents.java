package ru.bezzdars.bankbrest.enums;

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
  
  BANK_OPERATION_GET_SUCCESS("Успешное пополнение на %d$ из банка %s"),
  BANK_OPERATION_GET_FAILED("Не удалось пополнение на %d$ из банка %s"),
  BANK_OPERATION_PAY_SUCCESS("Успешный перевод на %d$ в банк %s"),
  BANK_OPERATION_PAY_FAILED("Не удалось совершить перевод на %d$ в банк %s");

  private final String description;
}
