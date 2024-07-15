package com.progresssoft.datawarehouse.fxdeals;

import com.progresssoft.datawarehouse.fxdeals.exception.DealExistsException;
import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import com.progresssoft.datawarehouse.fxdeals.repository.FxRepositoryInterface;
import com.progresssoft.datawarehouse.fxdeals.service.FXService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FxServiceTest {

    @Mock
    private FxRepositoryInterface fxRepository;

    @InjectMocks
    private FXService fxService;

    @Test
    public void testCreateDeal_NewDeal() throws DealExistsException {
        FXDeal fxDeal = new FXDeal(1, "USD", "EUR", 121.15);
        when(fxRepository.save(fxDeal)).thenReturn(fxDeal);
        FXDeal savedDeal = fxService.createDeal(fxDeal);
        assertEquals(savedDeal, fxDeal);
        verify(fxRepository, times(1)).save(fxDeal);
    }

    @Test
    public void testCreateDeal_DuplicateDeal() {
        FXDeal fxDeal = new FXDeal(1, "USD", "EUR", 121.15);
        when(fxRepository.existsByDealId(fxDeal.getDealId())).thenReturn(true);
        assertThrows(DealExistsException.class, () -> fxService.createDeal(fxDeal));
        verify(fxRepository, never()).save(any());
    }

}

