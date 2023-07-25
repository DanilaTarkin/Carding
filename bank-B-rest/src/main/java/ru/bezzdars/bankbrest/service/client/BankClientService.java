package ru.bezzdars.bankbrest.service.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import ru.bezzdars.bankbrest.config.ServiceProperties;
import ru.bezzdars.bankbrest.enums.EndPoints;
import ru.bezzdars.bankbrest.model.RestMessage;
import ru.bezzdars.bankbrest.util.CardingUtils;

/** Сервис взаимодействия с сервисом банка */
@Service
@RequiredArgsConstructor
public class BankClientService {
    private final RestTemplate client;
    private final ServiceProperties properties;
    private final NetworkClientService networkClientService;

    /**
     * Метод отправки сообщения в сервис логирования
     *
     * @param cash - сумма
     * @param from - название банка из которого выполняется перевод
     * @return адресс банка
     */
    public RestMessage pay(Long cash, String from) {
        var bankAddressFrom = networkClientService.getBankAddress(from);
        var url = CardingUtils.getUrl(bankAddressFrom, EndPoints.OPERATION_PAY, "cash=" + cash,
                "to=" + properties.getName());
        return client.getForEntity(url, RestMessage.class).getBody();
    }
}
