package com.progresssoft.datawarehouse.fxdeals.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageListenerService {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public MessageListenerService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public FXDeal receiveMessage() {
        String receivedDeal = jmsTemplate.receiveAndConvert("DEV.QUEUE.1").toString();
        ObjectMapper mapper = new ObjectMapper();
        FXDeal fxDeal = null;
        try {
            fxDeal = mapper.readValue(receivedDeal, FXDeal.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
        return fxDeal;
    }
}
