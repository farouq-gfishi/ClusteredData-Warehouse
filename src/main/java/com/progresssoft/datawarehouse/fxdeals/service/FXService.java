package com.progresssoft.datawarehouse.fxdeals.service;

import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import com.progresssoft.datawarehouse.fxdeals.repository.FXRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FXService {

    FXRepository fxRepository;

    @Autowired
    public FXService(FXRepository fxRepository) {
        this.fxRepository = fxRepository;
    }

    public void add(FXDeal fxDeal) {
        fxRepository.save(fxDeal);
    }


}
