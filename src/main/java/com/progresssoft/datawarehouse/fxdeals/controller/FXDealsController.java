package com.progresssoft.datawarehouse.fxdeals.controller;

import com.progresssoft.datawarehouse.fxdeals.exception.DealExistsException;
import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import com.progresssoft.datawarehouse.fxdeals.service.FXService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FXDealsController {

    private final FXService fxService;

    @Autowired
    public FXDealsController(FXService fxService) {
        this.fxService = fxService;
    }

    @PostMapping("/save-deal")
    public void saveFXDeal(@Valid @RequestBody FXDeal fxDeal) throws DealExistsException {
        fxService.saveDeal(fxDeal);
    }
}
