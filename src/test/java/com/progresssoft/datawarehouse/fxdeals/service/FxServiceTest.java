package com.progresssoft.datawarehouse.fxdeals.service;

import com.progresssoft.datawarehouse.fxdeals.exception.DealExistsException;
import com.progresssoft.datawarehouse.fxdeals.exception.DealNotFoundException;
import com.progresssoft.datawarehouse.fxdeals.exception.FiledNotFoundException;
import com.progresssoft.datawarehouse.fxdeals.exception.PaginationValueException;
import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import com.progresssoft.datawarehouse.fxdeals.repository.FxRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FxServiceTest {

    private final FxRepository fxRepository = mock(FxRepository.class);

    private final FXService fxService = new FXService(fxRepository);

    @Test
    public void FxService_SaveDeal_ReturnsFxDeal() throws DealExistsException {
        FXDeal fxDeal = new FXDeal(1, "USD", "EUR", 121.15);
        when(fxRepository.findByDealId(fxDeal.getDealId())).thenReturn(false);
        when(fxRepository.save(fxDeal)).thenReturn(fxDeal);
        FXDeal savedDeal = fxService.saveDeal(fxDeal);
        assertNotNull(savedDeal);
        assertEquals(savedDeal, fxDeal);
    }

    @Test
    public void FxService_SaveDeal_DuplicateDeal() {
        FXDeal fxDeal = new FXDeal(1, "USD", "EUR", 121.15);
        when(fxRepository.findByDealId(fxDeal.getDealId())).thenReturn(true);
        assertThrows(DealExistsException.class, () -> fxService.saveDeal(fxDeal));
    }

    @Test
    public void FxService_GetFXDeal_ReturnsFxDeal() throws DealNotFoundException {
        int dealId = 1;
        FXDeal fxDeal = new FXDeal(dealId, "USD", "EUR", 121.15);
        when(fxRepository.findByDealId(dealId)).thenReturn(true);
        when(fxRepository.getDealById(dealId)).thenReturn(fxDeal);
        FXDeal retrievedDeal = fxService.getFXDealByDealId(dealId);
        assertNotNull(retrievedDeal);
        assertEquals(retrievedDeal, fxDeal);
    }

    @Test
    public void FxService_GetFXDeal_DealNotFound() {
        int dealId = 1;
        when(fxRepository.findByDealId(dealId)).thenReturn(false);
        assertThrows(DealNotFoundException.class, () -> fxService.getFXDealByDealId(dealId));
    }

    @Test
    public void FxService_GetAllFXDealsSorted_ReturnsSortedDeals() throws FiledNotFoundException {
        String field = "dealId";
        List<FXDeal> fxDeals = Arrays.asList(
                new FXDeal(1, "USD", "EUR", 121.15),
                new FXDeal(2, "EUR", "USD", 112.50)
        );
        when(fxRepository.findAllBySorting(field)).thenReturn(fxDeals);
        List<FXDeal> sortedDeals = fxService.getAllFXDealsSorted(field);
        assertNotNull(sortedDeals);
        assertEquals(sortedDeals.size(), 2);
        assertEquals(sortedDeals.get(0).getDealId(), 1);
        assertEquals(sortedDeals.get(1).getDealId(), 2);
    }

    @Test
    public void FxService_GetAllFXDealsSorted_FieldNotFound() {
        String field = "invalidField";
        assertThrows(FiledNotFoundException.class, () -> fxService.getAllFXDealsSorted(field));
    }

    @Test
    public void FxService_GetAllFXDealsWithPagination_ReturnsPagedResults() throws FiledNotFoundException, PaginationValueException {
        int offset = 0;
        int pageSize = 10;
        List<FXDeal> fxDeals = Arrays.asList(
                new FXDeal(1, "USD", "EUR", 121.15),
                new FXDeal(2, "EUR", "USD", 112.50)
        );
        Page<FXDeal> page = new PageImpl<>(fxDeals);
        when(fxRepository.findAllWithPagination(offset, pageSize)).thenReturn(page);
        List<FXDeal> pagedDeals = fxService.getAllFXDealsWithPagination(offset, pageSize);
        assertNotNull(pagedDeals);
        assertEquals(pagedDeals.size(), 2);
        assertEquals(pagedDeals.get(0).getDealId(), 1);
        assertEquals(pagedDeals.get(1).getDealId(), 2);
    }

    @Test
    public void FxService_GetAllFXDealsWithPagination_InvalidOffsetOrPageSize() {
        int offset = -1;
        int pageSize = 0;
        assertThrows(PaginationValueException.class, () -> fxService.getAllFXDealsWithPagination(offset, pageSize));
    }

    @Test
    public void FxService_GetAllFXDealsWithPagination_NoDealsFound() {
        int offset = 0;
        int pageSize = 10;
        Page<FXDeal> emptyPage = new PageImpl<>(Collections.emptyList());
        when(fxRepository.findAllWithPagination(offset, pageSize)).thenReturn(emptyPage);
        assertThrows(FiledNotFoundException.class, () -> fxService.getAllFXDealsWithPagination(offset, pageSize));
    }

    @Test
    public void FxService_GetAllFXDealsSortedWithPagination_ReturnsPagedResults() throws FiledNotFoundException, PaginationValueException {
        int offset = 0;
        int pageSize = 10;
        String field = "amountDeal";
        List<FXDeal> fxDeals = Arrays.asList(
                new FXDeal(1, "USD", "EUR", 121.15),
                new FXDeal(2, "EUR", "USD", 112.50)
        );
        Page<FXDeal> page = new PageImpl<>(fxDeals);
        Pageable paging = PageRequest.of(offset, pageSize, Sort.by(field));
        when(fxRepository.findAllSortedWithPagination(paging)).thenReturn(page);
        List<FXDeal> pagedDeals = fxService.getAllFXDealsSortedWithPagination(offset, pageSize, field);
        assertNotNull(pagedDeals);
        assertEquals(pagedDeals.size(), 2);
        assertEquals(pagedDeals.get(0).getDealId(), 1);
        assertEquals(pagedDeals.get(1).getDealId(), 2);
    }

    @Test
    public void FxService_GetAllFXDealsSortedWithPagination_InvalidOffsetOrPageSize() {
        int offset = -1;
        int pageSize = 0;
        String field = "amountDeal";
        assertThrows(PaginationValueException.class, () -> fxService.getAllFXDealsSortedWithPagination(offset, pageSize, field));
    }

    @Test
    public void FxService_GetAllFXDealsSortedWithPagination_NoDealsFound() {
        int offset = 0;
        int pageSize = 10;
        String field = "amountDeal";
        Page<FXDeal> emptyPage = new PageImpl<>(Collections.emptyList());
        Pageable paging = PageRequest.of(offset, pageSize, Sort.by(field));
        when(fxRepository.findAllSortedWithPagination(paging)).thenReturn(emptyPage);
        assertThrows(FiledNotFoundException.class, () -> fxService.getAllFXDealsSortedWithPagination(offset, pageSize, field));
    }

    @Test
    public void FxService_GetAllFXDealsSortedWithPagination_FieldNotFound() {
        int offset = 0;
        int pageSize = 2;
        String field = "unknownField";
        List<FXDeal> fxDeals = Arrays.asList(
                new FXDeal(1, "USD", "EUR", 121.15),
                new FXDeal(2, "EUR", "USD", 112.50)
        );
        Page<FXDeal> page = new PageImpl<>(fxDeals);
        Pageable paging = PageRequest.of(offset, pageSize, Sort.by(field));
        when(fxRepository.findAllSortedWithPagination(paging)).thenReturn(page);
        assertThrows(FiledNotFoundException.class, () -> fxService.getAllFXDealsSortedWithPagination(offset, pageSize, field));
    }

}

