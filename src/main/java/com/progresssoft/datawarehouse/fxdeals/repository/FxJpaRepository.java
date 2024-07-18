package com.progresssoft.datawarehouse.fxdeals.repository;


import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface FxJpaRepository extends JpaRepository<FXDeal, Integer>, PagingAndSortingRepository<FXDeal, Integer> {
    boolean existsByDealId(int dealId);
    FXDeal getByDealId(int dealId);

}
