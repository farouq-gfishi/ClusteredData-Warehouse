package com.progresssoft.datawarehouse.fxdeals.repository;

import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
@ComponentScan
public class MysqlRepositoryTest {

    @Autowired
    private FxRepository fxRepository;

    @Test
    public void MysqlRepository_FindByDealId_ReturnTrue() {
        FXDeal fxDeal = new FXDeal(1, "USD", "EUR", 142.1);
        fxRepository.save(fxDeal);
        boolean savedDeal = fxRepository.findByDealId(fxDeal.getDealId());
        Assertions.assertTrue(savedDeal);
    }

    @Test
    public void MysqlRepository_FindByDealId_ReturnFalse() {
        FXDeal fxDeal = new FXDeal(1, "USD", "EUR", 142.1);
        fxRepository.save(fxDeal);
        boolean savedDeal = fxRepository.findByDealId(2);
        Assertions.assertFalse(savedDeal);
    }

    @Test
    public void MysqlRepository_Save_ReturnSavedDeal() {
        FXDeal fxDeal = new FXDeal(1, "USD", "EUR", 142.1);
        FXDeal savedDeal = fxRepository.save(fxDeal);
        Assertions.assertNotNull(savedDeal);
        Assertions.assertTrue(savedDeal.getId() >= 0);
    }

    @Test
    public void MysqlRepository_GetDealById_ReturnFxDeal() {
        FXDeal fxDeal = new FXDeal(1, "USD", "EUR", 142.1);
        fxRepository.save(fxDeal);
        FXDeal retrievedDeal = fxRepository.getDealById(fxDeal.getDealId());
        Assertions.assertNotNull(retrievedDeal);
        Assertions.assertEquals(fxDeal.getDealId(), retrievedDeal.getDealId());
        Assertions.assertEquals(fxDeal.getFromCurrency(), retrievedDeal.getFromCurrency());
        Assertions.assertEquals(fxDeal.getToCurrency(), retrievedDeal.getToCurrency());
        Assertions.assertEquals(fxDeal.getAmountDeal(), retrievedDeal.getAmountDeal());
    }

    @Test
    public void MysqlRepository_GetDealById_ReturnNull() {
        FXDeal fxDeal = new FXDeal(1, "USD", "EUR", 142.1);
        fxRepository.save(fxDeal);
        FXDeal retrievedDeal = fxRepository.getDealById(2);
        Assertions.assertNull(retrievedDeal);
    }

    @Test
    public void MysqlRepository_FindAllBySorting_ReturnSortedDeals() {
        FXDeal fxDeal1 = new FXDeal(1, "USD", "EUR", 142.1);
        FXDeal fxDeal2 = new FXDeal(2, "EUR", "USD", 112.5);
        fxRepository.save(fxDeal2);
        fxRepository.save(fxDeal1);
        List<FXDeal> sortedDeals = fxRepository.findAllBySorting("dealId");
        Assertions.assertEquals(2, sortedDeals.size());
        Assertions.assertEquals(1, sortedDeals.get(0).getDealId());
        Assertions.assertEquals(2, sortedDeals.get(1).getDealId());
    }

    @Test
    public void MysqlRepository_FindAllWithPagination_ReturnPagedResults() {
        FXDeal fxDeal1 = new FXDeal(1, "USD", "EUR", 142.1);
        FXDeal fxDeal2 = new FXDeal(2, "EUR", "USD", 112.5);
        FXDeal fxDeal3 = new FXDeal(3, "GBP", "USD", 98.7);
        fxRepository.save(fxDeal1);
        fxRepository.save(fxDeal2);
        fxRepository.save(fxDeal3);
        Page<FXDeal> pageResult = fxRepository.findAllWithPagination(0, 2);
        Assertions.assertEquals(2, pageResult.getSize());
        Assertions.assertEquals(3, pageResult.getTotalElements());
        Assertions.assertEquals(fxDeal1.getDealId(), pageResult.getContent().get(0).getDealId());
        Assertions.assertEquals(fxDeal2.getDealId(), pageResult.getContent().get(1).getDealId());
    }

    @Test
    public void MysqlRepository_FindAllSortedWithPagination_ReturnPagedResults() {
        FXDeal fxDeal1 = new FXDeal(1, "USD", "EUR", 142.1);
        FXDeal fxDeal2 = new FXDeal(2, "EUR", "USD", 112.5);
        FXDeal fxDeal3 = new FXDeal(3, "GBP", "USD", 98.7);
        fxRepository.save(fxDeal1);
        fxRepository.save(fxDeal2);
        fxRepository.save(fxDeal3);
        int offset = 0;
        int pageSize = 2;
        String field = "amountDeal";
        Pageable paging = PageRequest.of(offset, pageSize, Sort.by(field));
        Page<FXDeal> pageResult = fxRepository.findAllSortedWithPagination(paging);
        Assertions.assertEquals(2, pageResult.getSize());
        Assertions.assertEquals(fxDeal3.getDealId(), pageResult.getContent().get(0).getDealId());
        Assertions.assertEquals(fxDeal2.getDealId(), pageResult.getContent().get(1).getDealId());
    }

}
