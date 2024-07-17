package com.progresssoft.datawarehouse.fxdeals.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.progresssoft.datawarehouse.fxdeals.exception.DealExistsException;
import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import com.progresssoft.datawarehouse.fxdeals.service.FXService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(FXService.class);

    private final FXService fxService;

    @Autowired
    public MessageReceiver(FXService fxService) {
        this.fxService = fxService;
    }

    @JmsListener(destination = "DEV.QUEUE.1")
    public void receiveMessage(String jsonDeal) {
        try {
            logger.info("Received: " + jsonDeal);
            FXDeal fxDeal = toFXDeal(jsonDeal);
            logger.info("Saving deal... ");
            fxService.saveDeal(fxDeal);
        } catch (JsonProcessingException | DealExistsException | RuntimeException e) {
            logger.error(e.getMessage());
        }
    }

    private FXDeal toFXDeal(String jsonDeal) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonDeal, FXDeal.class);
    }
}
