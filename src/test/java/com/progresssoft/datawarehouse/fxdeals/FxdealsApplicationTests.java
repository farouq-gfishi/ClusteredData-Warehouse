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
		Assertions.assertThrows(ValidationException.class, () -> fxService.createDeal(fxDeal));
	}

	@Test
	public void testFxDealWithNullToCurrency() {
		FXDeal fxDeal = new FXDeal();
		fxDeal.setDealId(2);
		fxDeal.setFromCurrency("EUR");
		fxDeal.setAmountDeal(10.2);
		Assertions.assertThrows(ValidationException.class, () -> fxService.createDeal(fxDeal));
	}

	@Test
	public void testFxDealWithNullDealAmount() {
		FXDeal fxDeal = new FXDeal();
		fxDeal.setDealId(3);
		fxDeal.setFromCurrency("EUR");
		fxDeal.setFromCurrency("USD");
		Assertions.assertThrows(ValidationException.class, () -> fxService.createDeal(fxDeal));
	}

	@Test
	public void testFxDealWithAllFields() {
		FXDeal fxDeal = new FXDeal(4, "EUR", "USD", 102.45);
		Assertions.assertDoesNotThrow(() -> fxService.createDeal(fxDeal));
	}

	@Test
	public void testValidDealId() {
		FXDeal fxDeal = new FXDeal(5, "EUR", "USD", 102.45);
		Assertions.assertDoesNotThrow(() -> fxService.createDeal(fxDeal));
	}

	@Test
	public void testInValidDealId() {
		FXDeal fxDeal = new FXDeal(-5, "EUR", "USD", 102.45);
		Assertions.assertThrows(ValidationException.class, () -> fxService.createDeal(fxDeal));
	}

	@Test
	public void testValidFromCurrency() {
		FXDeal fxDeal = new FXDeal(6, "EUR", "USD", 102.45);
		Assertions.assertDoesNotThrow(() -> fxService.createDeal(fxDeal));
	}

	@Test
	public void testInValidFromCurrency() {
		FXDeal fxDeal = new FXDeal(6, "KKK", "USD", 102.45);
		Assertions.assertThrows(ValidationException.class, () -> fxService.createDeal(fxDeal));
	}

	@Test
	public void testValidToCurrency() {
		FXDeal fxDeal = new FXDeal(7, "EUR", "USD", 102.45);
		Assertions.assertDoesNotThrow(() -> fxService.createDeal(fxDeal));
	}

	@Test
	public void testInValidToCurrency() {
		FXDeal fxDeal = new FXDeal(6, "EUR", "KKK", 102.45);
		Assertions.assertThrows(ValidationException.class, () -> fxService.createDeal(fxDeal));
	}

	@Test
	public void testValidDealAmount() {
		FXDeal fxDeal = new FXDeal(8, "EUR", "USD", 102.45);
		Assertions.assertDoesNotThrow(() -> fxService.createDeal(fxDeal));
	}

	@Test
	public void testInValidDealAmount() {
		FXDeal fxDeal = new FXDeal(8, "EUR", "USD", -102.45);
		Assertions.assertThrows(ValidationException.class, () -> fxService.createDeal(fxDeal));
	}

}

