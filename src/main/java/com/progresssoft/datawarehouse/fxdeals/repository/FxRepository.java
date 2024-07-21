package com.progresssoft.datawarehouse.fxdeals.repository;

import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FxRepository {
    boolean findByDealId(int dealId);
    FXDeal save(FXDeal fxDeal);
    FXDeal getDealById(int dealId);
    List<FXDeal> findAllBySorting(String field);
    Page<FXDeal> findAllWithPagination(int offset, int pageSize);
    Page<FXDeal> findAllSortedWithPagination(Pageable pageable);
}
