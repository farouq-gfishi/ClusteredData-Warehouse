package com.progresssoft.datawarehouse.fxdeals.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.progresssoft.datawarehouse.fxdeals.exception.DealExistsException;
import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import com.progresssoft.datawarehouse.fxdeals.service.FXService;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageReceiverTest {

    private final FXService fxService = mock(FXService.class);

    private final MessageReceiver messageReceiver = new MessageReceiver(fxService);

    @Test
    public void testReceiveMessage_ValidDeal() throws JsonProcessingException, DealExistsException {
        String jsonDeal = "{\"dealId\": 1, \"fromCurrency\": \"USD\", \"toCurrency\": \"EUR\", \"amountDeal\": 100.0}";
        ArgumentCaptor<FXDeal> fxDealArgumentCaptor = ArgumentCaptor.forClass(FXDeal.class);
        when(fxService.saveDeal(fxDealArgumentCaptor.capture())).then(AdditionalAnswers.returnsFirstArg());
        messageReceiver.receiveMessage(jsonDeal);
        FXDeal savedDeal = fxDealArgumentCaptor.getValue();
        assertEquals("EUR", savedDeal.getToCurrency());
        assertEquals("USD", savedDeal.getFromCurrency());
        assertEquals(1, savedDeal.getDealId());
        assertEquals(100.0d, savedDeal.getAmountDeal());
    }
}