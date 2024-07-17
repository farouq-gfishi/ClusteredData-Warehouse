package com.progresssoft.datawarehouse.fxdeals.repository;

import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import org.springframework.stereotype.Repository;

@Repository
public interface FxRepository {
    boolean existsByDealId(int dealId);
    FXDeal save(FXDeal fxDeal);
}
