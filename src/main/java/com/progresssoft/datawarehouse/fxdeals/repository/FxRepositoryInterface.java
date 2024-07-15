package com.progresssoft.datawarehouse.fxdeals.repository;

import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import org.springframework.stereotype.Repository;

@Repository
public interface FxRepositoryInterface {
    boolean existsByDealId(int dealId);
    FXDeal save(FXDeal fxDeal);
}
