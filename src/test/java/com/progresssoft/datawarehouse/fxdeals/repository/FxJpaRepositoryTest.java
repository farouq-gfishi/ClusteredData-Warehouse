package com.progresssoft.datawarehouse.fxdeals.repository;

import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class FxJpaRepositoryTest {

    @Autowired
    private FxJpaRepository FxJpaRepository;

    @Test
    public void FxJpaRepository_Save_ReturnsSavedDeal() {
        FXDeal fxDeal = new FXDeal(1, "USD", "EUR", 142.1);
        FXDeal savedDeal = FxJpaRepository.save(fxDeal);
        Assertions.assertNotNull(savedDeal);
        Assertions.assertTrue(savedDeal.getId() >= 0);
    }

    @Test
    public void FxJpaRepository_ExistById_ReturnsSavedDeal() {
        FXDeal fxDeal = new FXDeal(1, "USD", "EUR", 142.1);
        FxJpaRepository.save(fxDeal);
        FXDeal savedDeal = FxJpaRepository.findById(fxDeal.getId()).get();
        Assertions.assertNotNull(savedDeal);
        Assertions.assertEquals(fxDeal.getId(), savedDeal.getId());
    }
}
