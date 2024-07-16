package com.progresssoft.datawarehouse.fxdeals.repository;

import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@ComponentScan(basePackages = "com.progresssoft.datawarehouse.fxdeals.repository")
public class MysqlJpaRepositoryTest {

    @Autowired
    private FxRepositoryInterface fxRepositoryInterface;

    @Test
    public void MysqlJpaRepository_Save_ReturnSavedDeal() {
        FXDeal fxDeal = new FXDeal(1, "USD", "EUR", 142.1);
        FXDeal savedDeal = fxRepositoryInterface.save(fxDeal);
        Assertions.assertNotNull(savedDeal);
        Assertions.assertTrue(savedDeal.getId() >= 0);
    }

    @Test
    public void MysqlJpaRepository_ExistByDealId_ReturnBoolean() {
        FXDeal fxDeal = new FXDeal(1, "USD", "EUR", 142.1);
        fxRepositoryInterface.save(fxDeal);
        boolean savedDeal = fxRepositoryInterface.existsByDealId(fxDeal.getId());
        Assertions.assertTrue(savedDeal);
    }
}
