package com.progresssoft.datawarehouse.fxdeals.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.progresssoft.datawarehouse.fxdeals.exception.DealExistsException;
import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import com.progresssoft.datawarehouse.fxdeals.service.FXService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MessageReceiverTest {

    @Mock
    private FXService fxService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private MessageReceiver messageReceiver;

    @Test
    public void testReceiveMessage_ValidDeal() throws JsonProcessingException, DealExistsException {
        String jsonDeal = "{\"dealId\": 1, \"fromCurrency\": \"USD\", \"toCurrency\": \"EUR\", \"amountDeal\": 100.0}";
        FXDeal fxDeal = new FXDeal(1, "USD", "EUR", 100.0);
        when(objectMapper.readValue(jsonDeal, FXDeal.class)).thenReturn(fxDeal);
        when(fxService.saveDeal(fxDeal)).thenReturn(null);
        assertDoesNotThrow(() -> messageReceiver.receiveMessage(jsonDeal));
    }

}

