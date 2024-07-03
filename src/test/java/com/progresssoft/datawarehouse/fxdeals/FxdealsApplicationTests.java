package com.progresssoft.datawarehouse.fxdeals;

import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FxdealsApplicationTests {

	private FXDeal fxDeal;

	@BeforeEach
	public void setUp() {
		fxDeal = new FXDeal();
	}

	@Test
	public void testFromCurrencyNotNull() {
		Assertions.assertNull(fxDeal.getFromCurrency());
	}

	@Test
	public void testFromCurrencyNotEmpty() {
		Assertions.assertNull(fxDeal.getFromCurrency());
	}

	@Test
	public void testFromCurrencyContainsThreeLetters() {
		fxDeal.setFromCurrency("USD");
		Assertions.assertEquals(fxDeal.getFromCurrency().length(), 3);
	}

	@Test
	public void testFromCurrencyNotContainsThreeLetters() {
		fxDeal.setFromCurrency("ABCD");
		Assertions.assertNotEquals(fxDeal.getFromCurrency().length(), 3);
	}

	@Test
	public void testToCurrencyNotNull() {
		Assertions.assertNull(fxDeal.getToCurrency());
	}

	@Test
	public void testToCurrencyNotEmpty() {
		Assertions.assertNull(fxDeal.getToCurrency());
	}

	@Test
	public void testToCurrencyContainsThreeLetters() {
		fxDeal.setToCurrency("USD");
		Assertions.assertEquals(fxDeal.getToCurrency().length(), 3);
	}

	@Test
	public void testToCurrencyNotContainsThreeLetters() {
		fxDeal.setToCurrency("ABCD");
		Assertions.assertNotEquals(fxDeal.getToCurrency().length(), 3);
	}

	@Test
	public void testAmountGreaterThenZero() {
		fxDeal.setAmountDeal(1.0);
		Assertions.assertTrue(fxDeal.getAmountDeal() >= 0.0);
	}

	@Test
	public void testAmountEqualZero() {
		fxDeal.setAmountDeal(0.0);
		Assertions.assertTrue(fxDeal.getAmountDeal() >= 0.0);
	}

	@Test
	public void testAmountNotSmallerThanZero() {
		fxDeal.setAmountDeal(-1.0);
		Assertions.assertFalse(fxDeal.getAmountDeal() >= 0.0);
	}

}

