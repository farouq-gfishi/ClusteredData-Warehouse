package com.progresssoft.datawarehouse.fxdeals.repository;


import com.progresssoft.datawarehouse.fxdeals.model.FXDeal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FxJpaRepository extends JpaRepository<FXDeal, Integer> {
}
