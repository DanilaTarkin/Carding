package ru.bezzdars.bankbrest.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ru.bezzdars.bankbrest.model.RestMessage;
import ru.bezzdars.bankbrest.service.client.BankClientService;
import ru.bezzdars.bankbrest.service.client.LoggerClientService;
import ru.bezzdars.bankbrest.util.CardingUtils;
import ru.bezzdars.bankbrest.config.ServiceProperties;
import ru.bezzdars.bankbrest.enums.Contents;
import ru.bezzdars.bankbrest.enums.EndPoints;
import ru.bezzdars.bankbrest.enums.Levels;

@Log4j2
@Service
@RequiredArgsConstructor
public class OperationService {

    private final ServiceProperties properties;
    private final BankClientService bankClientService;
    private final LoggerClientService loggerClientService;

    private Long balance = 5500L;

    public RestMessage get(Long cash, String from) {
        RestMessage message;

        try {
            var messageFromBankPay = bankClientService.pay(cash, from);

            if (Objects.isNull(messageFromBankPay) || Levels.ERROR.equals(messageFromBankPay.getLevel())) {
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