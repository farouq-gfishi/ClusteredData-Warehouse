package com.progresssoft.datawarehouse.fxdeals.service;

import com.progresssoft.datawarehouse.fxdeals.exception.DealExistsException;
import com.progresssoft.datawarehouse.fxdeals.exception.DealNotFoundException;
import com.progresssoft.datawarehouse.fxdeals.exception.FiledNotFoundException;
import com.progresssoft.datawarehouse.fxdeals.exception.PaginationValueException;
import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import com.progresssoft.datawarehouse.fxdeals.repository.FxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class FXService {

    private static final Logger logger = LoggerFactory.getLogger(FXService.class);
    
    private final FxRepository fxRepository;

    @Autowired
    public FXService(FxRepository fxRepository) {
        this.fxRepository = fxRepository;
    }

    @Transactional
    public FXDeal saveDeal(FXDeal fxDeal) throws DealExistsException {
        if (dealExist(fxDeal.getDealId())) {
            logger.warn("FXDeal is already exist: {}", fxDeal);
            throw new DealExistsException("Deal is already exist");
        }
        logger.info("Saving FXDeal: {}", fxDeal);
        fxRepository.save(fxDeal);
        logger.info("FXDeal saved successfully: {}", fxDeal);
        return fxDeal;
    }

    // TODO: read about @Transactional(readOnly = true)
    public FXDeal getFXDealByDealId(int dealId) throws DealNotFoundException {
        if(dealExist(dealId)) {
            logger.info("Retrieving FXDeal: {}", dealId);
            return fxRepository.getDealById(dealId);
        }
        logger.warn("FXDeal not found");
        throw new DealNotFoundException("Deal is not exist");
    }

    public List<FXDeal> getAllFXDealsSorted(String field) throws FiledNotFoundException {
        if(!fieldExist(field)) {
            logger.warn("field not found");
            throw new FiledNotFoundException("Field not found");
        }
        logger.info("getting all FX Deals sorted");
        return fxRepository.findAllBySorting(field);
    }

    public List<FXDeal> getAllFXDealsWithPagination(int offset, int pageSize) throws FiledNotFoundException, PaginationValueException {
        if (offset < 0 || pageSize <= 0) {
            logger.warn("offset or page size is negative");
            throw new PaginationValueException("Offset must be greater than or equal to 0 and pageSize must be greater than 0");
        }
        Page<FXDeal> pageResult = fxRepository.findAllWithPagination(offset, pageSize);
        if (pageResult.isEmpty()) {
            logger.warn("No FX Deals found");
            throw new FiledNotFoundException("No FXDeals found for the given pagination");
        }
        logger.info("getting all FX Deals with pagination");
        return pageResult.getContent();
    }

    private boolean dealExist(int dealId) {
        return fxRepository.existsByDealId(dealId);
    }

    private boolean fieldExist(String field) {
        for(String classField:FXDeal.getFields()) {
            if(classField.equals(field)) {
                return true;
            }
        }
        return false;
    }

}