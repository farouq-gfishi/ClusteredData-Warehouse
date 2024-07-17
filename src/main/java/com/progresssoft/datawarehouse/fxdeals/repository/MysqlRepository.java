package com.progresssoft.datawarehouse.fxdeals.repository;

import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MysqlRepository implements FxRepository {

    private final FxJpaRepository fxJpaRepository;

    @Autowired
    public MysqlRepository(FxJpaRepository fxRepository) {
        this.fxJpaRepository = fxRepository;
    }

    @Override
    public boolean existsByDealId(int dealId) {
        return fxJpaRepository.existsByDealId(dealId);
    }

    @Override
    public FXDeal save(FXDeal fxDeal) {
        return fxJpaRepository.save(fxDeal);
    }
}
