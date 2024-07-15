package com.progresssoft.datawarehouse.fxdeals.service;

import com.progresssoft.datawarehouse.fxdeals.exception.DealExistsException;
import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import com.progresssoft.datawarehouse.fxdeals.repository.FxRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FXService {

    private static final Logger logger = LoggerFactory.getLogger(FXService.class);
    
    private final FxRepositoryInterface fxRepository;

    @Autowired
    public FXService(FxRepositoryInterface fxRepository) {
        this.fxRepository = fxRepository;
    }

    @Transactional
    public FXDeal createDeal(FXDeal fxDeal) throws DealExistsException {
        if (dealExist(fxDeal.getDealId())) {
            logger.info("FXDeal is already exist: {}", fxDeal);
            throw new DealExistsException("Deal is already exist");
        }
        logger.info("Saving FXDeal: {}", fxDeal);
        fxRepository.save(fxDeal);
        logger.info("FXDeal saved successfully: {}", fxDeal);
        return fxDeal;
    }

    private boolean dealExist(int dealId) {
        return fxRepository.existsByDealId(dealId);
    }


}
