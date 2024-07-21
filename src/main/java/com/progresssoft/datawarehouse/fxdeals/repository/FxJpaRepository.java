package com.progresssoft.datawarehouse.fxdeals.repository;


import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface FxJpaRepository extends JpaRepository<FXDeal, Integer>, PagingAndSortingRepository<FXDeal, Integer> {
    boolean existsByDealId(int dealId);
    FXDeal getByDealId(int dealId);
}
