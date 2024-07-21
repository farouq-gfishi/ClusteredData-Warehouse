package com.progresssoft.datawarehouse.fxdeals.controller;

import com.progresssoft.datawarehouse.fxdeals.exception.DealExistsException;
import com.progresssoft.datawarehouse.fxdeals.exception.DealNotFoundException;
import com.progresssoft.datawarehouse.fxdeals.exception.FiledNotFoundException;
import com.progresssoft.datawarehouse.fxdeals.exception.PaginationValueException;
import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import com.progresssoft.datawarehouse.fxdeals.service.FXService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FXDealsControllerTest {

    private final FXService fxService = mock(FXService.class);

    private final FXDealsController fxDealsController = new FXDealsController(fxService);

    @Test
    void FxController_SaveDeal_ReturnsFxDeal() throws DealExistsException {
        FXDeal fxDeal = new FXDeal(1, "EUR", "USD", 100.12);
        when(fxService.saveDeal(fxDeal)).thenReturn(fxDeal);
        FXDeal savedDeal = fxDealsController.saveFXDeal(fxDeal);
        Assertions.assertEquals(savedDeal, fxDeal);
    }

    @Test
    void FxController_GetFXDeal_ReturnsFxDeal() throws DealNotFoundException {
        int dealId = 1;
        FXDeal fxDeal = new FXDeal(1, "EUR", "USD", 100.12);
        when(fxService.getFXDealByDealId(dealId)).thenReturn(fxDeal);
        FXDeal returnedDeal = fxDealsController.getFXDeal(dealId);
        Assertions.assertEquals(fxDeal, returnedDeal);
    }

    @Test
    void FxController_GetFXDealsSorted_ReturnsFxDeal() throws FiledNotFoundException {
        String field = "field";
        List<FXDeal> fxDeals = new ArrayList<>(
                Arrays.asList(
                        new FXDeal(1, "EUR", "USD", 100.12),
                        new FXDeal(2, "USD", "EUR", 312.21)
                )
        );
        when(fxService.getAllFXDealsSorted(field)).thenReturn(fxDeals);
        List<FXDeal> returnedDeals = fxDealsController.getFXDealsSorted(field);
        Assertions.assertEquals(fxDeals, returnedDeals);
    }

    @Test
    void FxController_GetFXDealWithPagination_ReturnsFxDeal() throws FiledNotFoundException, PaginationValueException {
        int offset = 0;
        int pageSize = 10;
        List<FXDeal> fxDeals = new ArrayList<>(
                Arrays.asList(
                        new FXDeal(1, "EUR", "USD", 100.12),
                        new FXDeal(2, "USD", "EUR", 312.21)
                )
        );
        when(fxService.getAllFXDealsWithPagination(offset, pageSize)).thenReturn(fxDeals);
        List<FXDeal> returnedDeals = fxDealsController.getFXDealWithPagination(offset, pageSize);
        Assertions.assertEquals(fxDeals, returnedDeals);
    }

    @Test
    void FxController_GetFXDealSortedWithPagination_ReturnsFxDeals() throws FiledNotFoundException, PaginationValueException {
        int offset = 0;
        int pageSize = 10;
        String field = "amountDeal";
        List<FXDeal> fxDeals = new ArrayList<>(
                Arrays.asList(
                        new FXDeal(1, "EUR", "USD", 102.25),
                        new FXDeal(2, "USD", "EUR", 201.23)
                )
        );
        when(fxService.getAllFXDealsSortedWithPagination(offset, pageSize, field)).thenReturn(fxDeals);
        List<FXDeal> returnedDeals = fxDealsController.getFXDealSortedWithPagination(field, offset, pageSize);
        Assertions.assertEquals(fxDeals, returnedDeals);
    }
}
