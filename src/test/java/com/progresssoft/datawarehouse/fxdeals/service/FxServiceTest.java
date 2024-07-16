package com.progresssoft.datawarehouse.fxdeals.service;

import com.progresssoft.datawarehouse.fxdeals.exception.DealExistsException;
import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import com.progresssoft.datawarehouse.fxdeals.repository.FxRepositoryInterface;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class FxServiceTest {

    @Mock
    private FxRepositoryInterface fxRepository;

    @InjectMocks
    private FXService fxService;

    @Test
    public void FxService_CreateDeal_ReturnsFxDeal() throws DealExistsException {
        FXDeal fxDeal = new FXDeal(1, "USD", "EUR", 121.15);
        when(fxRepository.save(fxDeal)).thenReturn(fxDeal);
        FXDeal savedDeal = fxService.createDeal(fxDeal);
        assertNotNull(savedDeal);
        assertEquals(savedDeal, fxDeal);
        verify(fxRepository, times(1)).save(fxDeal);
    }

    @Test
    public void FxService_CreateDeal_DuplicateDeal() {
        FXDeal fxDeal = new FXDeal(1, "USD", "EUR", 121.15);
        when(fxRepository.existsByDealId(fxDeal.getDealId())).thenReturn(true);
        assertThrows(DealExistsException.class, () -> fxService.createDeal(fxDeal));
        verify(fxRepository, never()).save(any());
    }
}

