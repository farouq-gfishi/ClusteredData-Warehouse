package com.progresssoft.datawarehouse.fxdeals.controller;

import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import com.progresssoft.datawarehouse.fxdeals.service.FXService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class FXDealsController {

    private FXService fxService;

    @Autowired
    public FXDealsController(FXService fxService) {
        this.fxService = fxService;
    }

    @GetMapping("/get-form")
    public String getForm(Model model) {
        model.addAttribute("fxDeal", new FXDeal());
        return "form";
    }

    @PostMapping("/save")
    public String saveFXDeal(@Valid @ModelAttribute("fxDeal") FXDeal fxDeal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        fxService.add(fxDeal);
        return "redirect:/api/get-form";
    }
}
