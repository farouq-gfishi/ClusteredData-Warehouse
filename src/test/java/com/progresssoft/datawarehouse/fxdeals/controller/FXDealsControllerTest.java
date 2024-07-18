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

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FXDealsControllerTest {

    @Mock
    private FXService fxService;

    @InjectMocks
    private FXDealsController fxDealsController;

    @Test
    void testSaveFXDeal() throws DealExistsException {
        FXDeal fxDeal = new FXDeal(1, "EUR", "USD", 100.12);
        fxDealsController.saveFXDeal(fxDeal);
        Assertions.assertDoesNotThrow(() -> fxDealsController.saveFXDeal(fxDeal));
    }

    @Test
    void testGetFXDealById() throws DealNotFoundException {
        int dealId = 1;
        FXDeal fxDeal = new FXDeal(1, "EUR", "USD", 100.12);
        when(fxService.getFXDeal(dealId)).thenReturn(fxDeal);
        Assertions.assertEquals(fxDeal, fxDealsController.getFXDeal(dealId));
    }

    @Test
    void testGetFXDealsSorted() throws FiledNotFoundException {
        String field = "field";
        List<FXDeal> fxDeals = new ArrayList<>(
                Arrays.asList(
                        new FXDeal(1, "EUR", "USD", 100.12),
                        new FXDeal(2, "USD", "EUR", 312.21)
                )
        );
        when(fxService.getAllFXDealsSorted(field)).thenReturn(fxDeals);
        Assertions.assertEquals(fxDeals, fxDealsController.getFXDealsSorted(field));
    }

    @Test
    void testGetFXDealsWithPagination() throws FiledNotFoundException, PaginationValueException {
        int offset = 0;
        int pageSize = 10;
        List<FXDeal> fxDeals = new ArrayList<>(
                Arrays.asList(
                        new FXDeal(1, "EUR", "USD", 100.12),
                        new FXDeal(2, "USD", "EUR", 312.21)
                )
        );
        when(fxService.getAllFXDealsWithPagination(offset, pageSize)).thenReturn(fxDeals);
        Assertions.assertEquals(fxDeals, fxDealsController.getFXDealWithPagination(offset, pageSize));
    }
}
