package com.progresssoft.datawarehouse.fxdeals.exception;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Currency;
import java.util.Set;


// TODO: unit testing
public class ValidCurrencyCodeValidator implements ConstraintValidator<ValidCurrencyCode, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        try {
            Set<Currency> currencies = Currency.getAvailableCurrencies();
            return currencies.contains(Currency.getInstance(value));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
