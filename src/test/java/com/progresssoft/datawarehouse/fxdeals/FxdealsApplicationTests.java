package com.progresssoft.datawarehouse.fxdeals;

import com.progresssoft.datawarehouse.fxdeals.exception.DealExistsException;
import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import com.progresssoft.datawarehouse.fxdeals.service.FXService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class FxdealsApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private FXService fxService;

	@Test
	public void testSaveFXDealOkStatusCode() {
		FXDeal fxDeal = new FXDeal(1, "EUR", "USD", 100.12);
		given()
				.port(port)
				.basePath("/api")
				.contentType("application/json")
				.body(fxDeal)
				.when()
				.post("/save-deal")
				.then()
				.statusCode(200);
	}

	@Test
	public void testSaveFXDealBadRequestStatusCode() {
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
	public void testGetFXDealOkStatusCode() throws DealExistsException {
		int dealId = 2;
		fxService.saveDeal(new FXDeal(dealId, "USD", "EUR", 100.12));
		given()
				.port(port)
				.basePath("/api")
				.when()
				.get("/get-deal/{dealId}", dealId)
				.then()
				.statusCode(200);
	}

	@Test
	public void testGetFXDealBadRequestStatusCode() throws DealExistsException {
		int dealId = 3;
		int unavailableDealId = 89;
		fxService.saveDeal(new FXDeal(dealId, "USD", "EUR", 100.12));
		given()
				.port(port)
				.basePath("/api")
				.when()
				.get("/get-deal/{dealId}", unavailableDealId)
				.then()
				.statusCode(400);
	}

	@Test
	public void testGetFXDealsSortedOkStatusCode() throws DealExistsException {
		String field = "amountDeal";
		fxService.saveDeal(new FXDeal(4, "USD", "EUR", 99.15));
		fxService.saveDeal(new FXDeal(5, "USD", "EUR", 100.17));
		fxService.saveDeal(new FXDeal(6, "USD", "EUR", 120.85));
		given()
				.port(port)
				.basePath("/api")
				.when()
				.get("/get-deal/sorted-by/{field}", field)
				.then()
				.statusCode(200);
	}

	@Test
	public void testGetFXDealsSortedBadRequestStatusCode() throws DealExistsException {
		String field = "unavailableField";
		fxService.saveDeal(new FXDeal(7, "USD", "EUR", 99.15));
		fxService.saveDeal(new FXDeal(8, "USD", "EUR", 100.17));
		fxService.saveDeal(new FXDeal(9, "USD", "EUR", 120.85));
		given()
				.port(port)
				.basePath("/api")
				.when()
				.get("/get-deal/sorted-by/{field}", field)
				.then()
				.statusCode(400);
	}


	@Test
	public void testGetFXDealWithPaginationOkStatusCode() throws DealExistsException {
		int offset = 0;
		int pageSize = 2;
		fxService.saveDeal(new FXDeal(11, "USD", "EUR", 99.15));
		fxService.saveDeal(new FXDeal(12, "USD", "EUR", 100.17));
		fxService.saveDeal(new FXDeal(13, "USD", "EUR", 120.85));
		given()
				.port(port)
				.basePath("/api")
				.when()
				.get("/get-deal/pagination/{offset}/{pageSize}", offset, pageSize)
				.then()
				.statusCode(200);
	}

	@Test
	public void testGetFXDealWithPaginationBadRequestStatusCode() throws DealExistsException {
		int offset = 2;
		int pageSize = 20;
		fxService.saveDeal(new FXDeal(14, "USD", "EUR", 99.15));
		fxService.saveDeal(new FXDeal(15, "USD", "EUR", 100.17));
		fxService.saveDeal(new FXDeal(16, "USD", "EUR", 120.85));
		given()
				.port(port)
				.basePath("/api")
				.when()
				.get("/get-deal/pagination/{offset}/{pageSize}", offset, pageSize)
				.then()
				.statusCode(400);
	}


}