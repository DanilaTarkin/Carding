package ru.bezzdars.cardingrest.service;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.bezzdars.cardingrest.config.ServiceProperties;
import ru.bezzdars.cardingrest.enums.Contents;
import ru.bezzdars.cardingrest.enums.EndPoints;
import ru.bezzdars.cardingrest.enums.Levels;
import ru.bezzdars.cardingrest.model.RestMessage;
import ru.bezzdars.cardingrest.service.client.LoggerClientService;
import ru.bezzdars.cardingrest.service.client.NetworkClientService;
import ru.bezzdars.cardingrest.util.CardingUtils;

/** Сервис перевода средств между банками */
@Log4j2
@Service
@RequiredArgsConstructor
public class TransferService {

  private final RestTemplate client;
  private final ServiceProperties properties;
  private final NetworkClientService networkClientService;
  private final LoggerClientService loggerClientService;

  /**
   * Метод перевода средств из банка 'from' в банк 'to'
   *
   * @param from - название банка отправителя
   * @param to   - название банка получателя
   * @param cash - сумма для перевода
   * @return сообщение об результате выполнения транзакции перевода
   */
  public RestMessage transfer(String from, String to, Long cash) {
    RestMessage message;
    RestMessage messageFromBank = null;
    var bankAdrressTo = networkClientService.getBankAddress(to);
    var urlBankGet = CardingUtils.getUrl(bankAdrressTo, EndPoints.OPERATION_GET, "cash=" + cash, "from=" + from);

    try {
      messageFromBank = client.getForEntity(urlBankGet, RestMessage.class).getBody();
    } catch (Exception e) {
      log.error(CardingUtils.getLog(Contents.BANK_RESPONSE_FAILED, EndPoints.OPERATION_GET, to, to));
    }

    if (Objects.isNull(messageFromBank) || Levels.ERROR.equals(messageFromBank.getLevel())) {
      log.error(CardingUtils.getLog(Contents.TRANSACTION_FAILED, EndPoints.TRANSFER, properties.getName()));
      message = CardingUtils.getMessage(Contents.TRANSACTION_FAILED, Levels.ERROR, properties.getName());
    } else {
      log.error(CardingUtils.getLog(Contents.TRANSACTION_SUCCESS, EndPoints.TRANSFER, properties.getName()));
      message = CardingUtils.getMessage(Contents.TRANSACTION_SUCCESS, Levels.INFO, properties.getName());
    }

    loggerClientService.send(message);
    return message;
  }
}
