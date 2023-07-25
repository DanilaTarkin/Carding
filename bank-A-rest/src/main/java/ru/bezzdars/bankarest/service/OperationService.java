package ru.bezzdars.bankarest.service;

import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ru.bezzdars.bankarest.model.RestMessage;
import ru.bezzdars.bankarest.service.client.LoggerClientService;
import ru.bezzdars.bankarest.service.client.NetworkClientService;
import ru.bezzdars.bankarest.util.CardingUtils;
import ru.bezzdars.bankarest.config.ServiceProperties;
import ru.bezzdars.bankarest.enums.Contents;
import ru.bezzdars.bankarest.enums.EndPoints;
import ru.bezzdars.bankarest.enums.Levels;

@Log4j2
@Service
@RequiredArgsConstructor
public class OperationService {

    private final RestTemplate client;
    private final ServiceProperties properties;
    private final NetworkClientService networkClientService;
    private final LoggerClientService loggerClientService;

    private Long balance = 5500L;

    public RestMessage get(Long cash, String from) {
        RestMessage message;
        var addressFrom = networkClientService.getBankAddress(from);
        var url = CardingUtils.getUrl(addressFrom, EndPoints.OPERATION_PAY, "cash=" + cash, "to=" + properties.getName());

        try {
            var messageFromBank = client.getForEntity(url, RestMessage.class).getBody();

            if (Objects.isNull(messageFromBank) || Levels.ERROR.equals(messageFromBank.getLevel())) {
                throw new RuntimeException();
            }

            balance += cash;

            log.info(CardingUtils.getLog(Contents.BANK_OPERATION_GET_SUCCESS, EndPoints.OPERATION_GET, properties.getName(), cash, from));
            message = CardingUtils.getMessage(Contents.BANK_OPERATION_GET_SUCCESS, Levels.INFO, properties.getName(), cash, from);
        } catch (Exception e) {
            log.error(CardingUtils.getLog(Contents.BANK_OPERATION_GET_FAILED, EndPoints.OPERATION_GET, properties.getName(), cash, from));
            message = CardingUtils.getMessage(Contents.BANK_OPERATION_GET_FAILED, Levels.ERROR, properties.getName(), cash, from);
        }

        loggerClientService.send(message);
        return message;
    }

    public RestMessage pay(Long cash, String to) {
        RestMessage message;

        if (balance - cash < 0) {
            log.error(CardingUtils.getLog(Contents.BANK_OPERATION_PAY_FAILED, EndPoints.OPERATION_PAY, properties.getName(), cash, to));
            message = CardingUtils.getMessage(Contents.BANK_OPERATION_PAY_FAILED, Levels.ERROR, properties.getName(), cash, to);
        } else {
            balance -= cash;

            log.info(CardingUtils.getLog(Contents.BANK_OPERATION_PAY_SUCCESS, EndPoints.OPERATION_PAY, properties.getName(), cash, to));
            message = CardingUtils.getMessage(Contents.BANK_OPERATION_PAY_SUCCESS, Levels.INFO, properties.getName(), cash, to);
        }

        loggerClientService.send(message);
        return message;
    }

}