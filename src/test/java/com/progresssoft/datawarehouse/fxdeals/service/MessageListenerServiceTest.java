package com.progresssoft.datawarehouse.fxdeals.service;

import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class MessageListenerServiceTest {

    @Mock
    private JmsTemplate jmsTemplate;

    @InjectMocks
    private MessageListenerService messageListenerService;

    @Test
    public void MessageListenerService_ReceiveMessage_ReturnsFxDeal() {
        String jsonDeal = "{\n" +
                "    \"dealId\": 1,\n" +
                "    \"fromCurrency\": \"EUR\",\n" +
                "    \"toCurrency\": \"USD\",\n" +
                "    \"amountDeal\": 123.245\n" +
                "}";
        when(jmsTemplate.receiveAndConvert("DEV.QUEUE.1")).thenReturn(jsonDeal);
        FXDeal fxDeal = messageListenerService.receiveMessage();
        assertNotNull(fxDeal);
        assertEquals(1, fxDeal.getDealId());
        assertEquals("EUR", fxDeal.getFromCurrency());
        assertEquals("USD", fxDeal.getToCurrency());
        assertEquals(123.245, fxDeal.getAmountDeal());
        verify(jmsTemplate, times(1)).receiveAndConvert("DEV.QUEUE.1");
    }
}
