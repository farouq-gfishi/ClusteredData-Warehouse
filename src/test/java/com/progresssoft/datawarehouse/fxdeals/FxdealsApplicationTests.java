package com.progresssoft.datawarehouse.fxdeals;

import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import com.progresssoft.datawarehouse.fxdeals.service.FXService;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
class FxdealsApplicationTests {

	@Autowired
	private FXService fxService;

	@Test
	public void testFxDealWithNullFromCurrency() {
		FXDeal fxDeal = new FXDeal();
		fxDeal.setDealId(1);
		fxDeal.setToCurrency("USD");
		fxDeal.setAmountDeal(10.2);
		ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> fxService.createDeal(fxDeal));
		Assertions.assertTrue(validationException.getMessage().contains("wrong iso code"));
	}

	@Test
	public void testFxDealWithNullToCurrency() {
		FXDeal fxDeal = new FXDeal();
		fxDeal.setDealId(2);
		fxDeal.setFromCurrency("EUR");
		fxDeal.setAmountDeal(10.2);
		ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> fxService.createDeal(fxDeal));
		Assertions.assertTrue(validationException.getMessage().contains("wrong iso code"));
	}

	@Test
	public void testFxDealWithNullDealAmount() {
		FXDeal fxDeal = new FXDeal();
		fxDeal.setDealId(3);
		fxDeal.setFromCurrency("EUR");
		fxDeal.setFromCurrency("USD");
		ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> fxService.createDeal(fxDeal));
		Assertions.assertTrue(validationException.getMessage().contains("Deal Amount in ordering currency is required"));
	}

	@Test
	public void testInValidDealId() {
		FXDeal fxDeal = new FXDeal(-5, "EUR", "USD", 102.45);
		ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> fxService.createDeal(fxDeal));
		Assertions.assertTrue(validationException.getMessage().contains("Deal Id must be greater than or equal to zero"));
	}

	@Test
	public void testInValidFromCurrency() {
		FXDeal fxDeal = new FXDeal(6, "KKK", "USD", 102.45);
		ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> fxService.createDeal(fxDeal));
		Assertions.assertTrue(validationException.getMessage().contains("wrong iso code"));
	}

	@Test
	public void testInValidToCurrency() {
		FXDeal fxDeal = new FXDeal(6, "EUR", "KKK", 102.45);
		ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> fxService.createDeal(fxDeal));
		Assertions.assertTrue(validationException.getMessage().contains("wrong iso code"));
	}

	@Test
	public void testInValidDealAmount() {
		FXDeal fxDeal = new FXDeal(8, "EUR", "USD", -102.45);
		ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> fxService.createDeal(fxDeal));
		Assertions.assertTrue(validationException.getMessage().contains("Deal Amount must be greater than or equal to zero"));
	}

}