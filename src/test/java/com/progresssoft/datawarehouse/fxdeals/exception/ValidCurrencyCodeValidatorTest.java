package com.progresssoft.datawarehouse.fxdeals.exception;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


public class ValidCurrencyCodeValidatorTest {

    private final ValidCurrencyCodeValidator validator = new ValidCurrencyCodeValidator();

    private ConstraintValidatorContext context;

    @Test
    void testValidCurrencyCode() {
        assertTrue(validator.isValid("USD", context));
        assertTrue(validator.isValid("EUR", context));
        assertTrue(validator.isValid("JPY", context));
    }

    @Test
    void testInvalidCurrencyCode() {
        assertFalse(validator.isValid("XYZ", context));
        assertFalse(validator.isValid("123", context));
        assertFalse(validator.isValid("US", context));
    }

    @Test
    void testNullCurrencyCode() {
        assertFalse(validator.isValid(null, context));
    }

    @Test
    void testEmptyCurrencyCode() {
        assertFalse(validator.isValid("", context));
    }

    @Test
    void testBlankCurrencyCode() {
        assertFalse(validator.isValid("   ", context));
    }

}
