package com.progresssoft.datawarehouse.fxdeals;

import com.progresssoft.datawarehouse.fxdeals.listener.CleanupH2DatabaseTestListener;
import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import com.progresssoft.datawarehouse.fxdeals.repository.FxRepository;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, CleanupH2DatabaseTestListener.class})
class FxdealsApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private FxRepository fxRepository;

	@BeforeEach
	public void setUp() {
		fxRepository.save(new FXDeal(1, "EUR", "USD", 134.43));
		fxRepository.save(new FXDeal(2, "USD", "EUR", 110.54));
		fxRepository.save(new FXDeal(3, "EUR", "JPY", 99.43));
		fxRepository.save(new FXDeal(4, "JPY", "EUR", 55.32));
	}

	@Test
	public void FXDealsApplication_SaveDeal_ReturnsFxDeal() {
		FXDeal fxDeal = new FXDeal(5, "EUR", "JOD", 150.25);
		given()
				.port(port)
				.basePath("/api")
				.body(fxDeal)
				.contentType("application/json")
				.when()
				.post("/save-deal")
				.then()
				.statusCode(200)
				.assertThat().body("dealId", equalTo(5)).and()
				.assertThat().body("fromCurrency", equalTo("EUR")).and()
				.assertThat().body("toCurrency", equalTo("JOD")).and()
				.assertThat().body("amountDeal", equalTo(150.25F));
	}

	@Test
	public void FXDealsApplication_SaveDeal_BadRequest() {
		FXDeal fxDeal = new FXDeal(-1, "EUR", "USD", 100.12);
		given()
				.port(port)
				.basePath("/api")
				.contentType("application/json")
				.body(fxDeal)
				.when()
				.post("/save-deal")
				.then()
				.statusCode(400);
	}

	@Test
	public void FXDealsApplication_GetFxDeal_ReturnFxDeal() {
		int dealId = 1;
		given()
				.port(port)
				.basePath("/api")
				.when()
				.get("/get-deal/{dealId}", dealId)
				.then()
				.statusCode(200)
				.assertThat().body("dealId", equalTo(dealId)).and()
				.assertThat().body("fromCurrency", equalTo("EUR")).and()
				.assertThat().body("toCurrency", equalTo("USD")).and()
				.assertThat().body("amountDeal", equalTo(134.43f));
	}

	@Test
	public void FXDealsApplication_GetFxDeal_BadRequest() {
		int unavailableDealId = -1;
		given()
				.port(port)
				.basePath("/api")
				.when()
				.get("/get-deal/{dealId}", unavailableDealId)
				.then()
				.statusCode(400);
	}

	@Test
	public void FXDealsApplication_GetFXDealsSorted_ReturnSortedFxDeals() {
		String field = "amountDeal";
		Response response = given()
				.port(port)
				.basePath("/api")
				.when()
				.get("/get-deal/sorted-by/{field}", field)
				.then()
				.statusCode(200)
				.extract().response();
		response.then()
				.body("$", hasSize(4))
				.body("[0].amountDeal", equalTo(55.32f))
				.body("[1].amountDeal", equalTo(99.43f))
				.body("[2].amountDeal", equalTo(110.54f))
				.body("[3].amountDeal", equalTo(134.43f));
	}

	@Test
	public void FXDealsApplication_GetFXDealsSorted_BadRequest() {
		String field = "unavailableField";
		given()
				.port(port)
				.basePath("/api")
				.when()
				.get("/get-deal/sorted-by/{field}", field)
				.then()
				.statusCode(400);
	}


	@Test
	public void FXDealsApplication_GetFXDealWithPagination_ReturnPaginationDeals() {
		int offset = 0;
		int pageSize = 2;
		Response response = given()
				.port(port)
				.basePath("/api")
				.when()
				.get("/get-deal/pagination/{offset}/{pageSize}", offset, pageSize)
				.then()
				.statusCode(200)
				.extract().response();
		response.then()
				.body("$", hasSize(2))
				.body("[0].amountDeal", equalTo(134.43f))
				.body("[1].amountDeal", equalTo(110.54f));
	}

	@Test
	public void FXDealsApplication_GetFXDealWithPagination_BadRequest() {
		int offset = 2;
		int pageSize = 20;
		given()
				.port(port)
				.basePath("/api")
				.when()
				.get("/get-deal/pagination/{offset}/{pageSize}", offset, pageSize)
				.then()
				.statusCode(400);
	}
}