package com.progresssoft.datawarehouse.fxdeals.controller;

import com.progresssoft.datawarehouse.fxdeals.exception.DealExistsException;
import com.progresssoft.datawarehouse.fxdeals.exception.DealNotFoundException;
import com.progresssoft.datawarehouse.fxdeals.exception.FiledNotFoundException;
import com.progresssoft.datawarehouse.fxdeals.exception.PaginationValueException;
import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import com.progresssoft.datawarehouse.fxdeals.service.FXService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FXDealsController {

    private final FXService fxService;

    @Autowired
    public FXDealsController(FXService fxService) {
        this.fxService = fxService;
    }

    @PostMapping("/save-deal")
    public FXDeal saveFXDeal(@Valid @RequestBody FXDeal fxDeal) throws DealExistsException {
        return fxService.saveDeal(fxDeal);
    }

    @GetMapping("/get-deal/{dealId}")
    public FXDeal getFXDeal(@PathVariable int dealId) throws DealNotFoundException {
        return fxService.getFXDealByDealId(dealId);
    }

    @GetMapping("/get-deal/sorted-by/{field}")
    public List<FXDeal> getFXDealsSorted(@PathVariable String field) throws FiledNotFoundException {
        return fxService.getAllFXDealsSorted(field);
    }

    @GetMapping("/get-deal/pagination/{offset}/{pageSize}")
    public List<FXDeal> getFXDealWithPagination(@PathVariable int offset, @PathVariable int pageSize) throws FiledNotFoundException, PaginationValueException {
        return fxService.getAllFXDealsWithPagination(offset, pageSize);
    }
}
