package com.progresssoft.datawarehouse.fxdeals.repository;

import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FXRepository extends JpaRepository<FXDeal, Integer> {
    boolean existsByDealId(int dealId);
}
