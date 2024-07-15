package com.progresssoft.datawarehouse.fxdeals.repository;

import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MysqlJpaRepository implements FxRepositoryInterface {

    private final FxJpaRepository fxJpaRepository;

    @Autowired
    public MysqlJpaRepository(FxJpaRepository fxRepository) {
        this.fxJpaRepository = fxRepository;
    }

    @Override
    public boolean existsByDealId(int dealId) {
        return fxJpaRepository.existsById(dealId);
    }

    @Override
    public void save(FXDeal fxDeal) {
        fxJpaRepository.save(fxDeal);
    }
}
