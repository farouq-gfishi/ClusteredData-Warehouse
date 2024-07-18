package com.progresssoft.datawarehouse.fxdeals.repository;

import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public FXDeal getDealById(int dealId) {
        return fxJpaRepository.getByDealId(dealId);
    }

    @Override
    public List<FXDeal> findAllBySorting(String field) {
        return fxJpaRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    @Override
    public Page<FXDeal> findAllWithPagination(int offset, int pageSize) {
        return fxJpaRepository.findAll(PageRequest.of(offset,pageSize));
    }

}
