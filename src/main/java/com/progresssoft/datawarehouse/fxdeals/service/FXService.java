package com.progresssoft.datawarehouse.fxdeals.service;

import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import com.progresssoft.datawarehouse.fxdeals.repository.FXRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FXService {

    private static final Logger logger = LoggerFactory.getLogger(FXService.class);
    
    private final FXRepository fxRepository;

    @Autowired
    public FXService(FXRepository fxRepository) {
        this.fxRepository = fxRepository;
    }

    @Transactional
    public boolean createDeal(FXDeal fxDeal) {
        if (!dealExist(fxDeal.getDealId())) {
            logger.info("Saving FXDeal: {}", fxDeal);
            fxRepository.save(fxDeal);
            logger.info("FXDeal saved successfully: {}", fxDeal);
            return true;
        }
        else {
            logger.info("FXDeal is already exist: {}", fxDeal);
            return false;
        }
    }

    private boolean dealExist(int dealId) {
        return fxRepository.existsByDealId(dealId);
    }


}
