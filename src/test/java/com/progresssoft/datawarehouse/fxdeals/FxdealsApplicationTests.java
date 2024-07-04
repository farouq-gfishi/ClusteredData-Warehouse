package com.progresssoft.datawarehouse.fxdeals;

import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import com.progresssoft.datawarehouse.fxdeals.repository.FXRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FxdealsApplicationTests {

	private FXDeal fxDeal;

	@Autowired
	private FXRepository fxRepository;

	@BeforeEach
	public void setUp() {
		fxDeal = new FXDeal();
	}

	@Test
	public void testFxDealNotEmpty() {
		Assertions.assertThrows(Exception.class, () -> fxRepository.save(fxDeal));
	}

	@Test
	public void testFxDealWithEmptyField() {
		fxDeal.setToCurrency("USD");
		fxDeal.setAmountDeal(100.12);
		// the from currency is empty
		Assertions.assertThrows(Exception.class, () -> fxRepository.save(fxDeal));
	}

	@Test
	public void testFxDealWithAllFields() {
		fxDeal.setFromCurrency("EUR");
		fxDeal.setToCurrency("USD");
		fxDeal.setAmountDeal(100.12);
		Assertions.assertDoesNotThrow(() -> fxRepository.save(fxDeal));
	}

	@Test
	public void testFromCurrencyContainsThreeLetters() {
		fxDeal.setFromCurrency("EUR");
		fxDeal.setToCurrency("USD");
		fxDeal.setAmountDeal(100.12);
		Assertions.assertDoesNotThrow(() -> fxRepository.save(fxDeal));
	}

	@Test
	public void testFromCurrencyDoesContainsThreeLetters() {
		fxDeal.setFromCurrency("EEUR");
		fxDeal.setToCurrency("USD");
		fxDeal.setAmountDeal(100.12);
		Assertions.assertThrows(Exception.class, () -> fxRepository.save(fxDeal));
	}

	@Test
	public void testToCurrencyDoesContainsThreeLetters() {
		fxDeal.setFromCurrency("EUR");
		fxDeal.setToCurrency("UUSD");
		fxDeal.setAmountDeal(100.12);
		Assertions.assertThrows(Exception.class, () -> fxRepository.save(fxDeal));
	}

	@Test
	public void testAmountGreaterThenZero() {
		fxDeal.setFromCurrency("EUR");
		fxDeal.setToCurrency("USD");
		fxDeal.setAmountDeal(1.01);
		Assertions.assertDoesNotThrow(() -> fxRepository.save(fxDeal));
	}

	@Test
	public void testAmountEqualZero() {
		fxDeal.setFromCurrency("EUR");
		fxDeal.setToCurrency("USD");
		fxDeal.setAmountDeal(0);
		Assertions.assertDoesNotThrow(() -> fxRepository.save(fxDeal));
	}

	@Test
	public void testAmountLessThanZero() {
		fxDeal.setFromCurrency("EUR");
		fxDeal.setToCurrency("USD");
		fxDeal.setAmountDeal(-1.5);
		Assertions.assertThrows(Exception.class, () -> fxRepository.save(fxDeal));
	}

}

