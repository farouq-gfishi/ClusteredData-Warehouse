package com.progresssoft.datawarehouse.fxdeals.controller;

import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import com.progresssoft.datawarehouse.fxdeals.service.FXService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FXDealsController {

    private final FXService fxService;

    @Autowired
    public FXDealsController(FXService fxService) {
        this.fxService = fxService;
    }

    @PostMapping("/create-deal")
    public ResponseEntity<String> saveFXDeal(@Valid @RequestBody FXDeal fxDeal) {
        boolean DealCreated = fxService.createDeal(fxDeal);
        if(DealCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).body("FXDeal saved successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("FXDeal already exists");
        }
    }
}
