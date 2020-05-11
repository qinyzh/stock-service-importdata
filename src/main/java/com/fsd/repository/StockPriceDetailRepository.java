package com.fsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fsd.domain.StockPriceDetail;

public interface StockPriceDetailRepository extends JpaRepository<StockPriceDetail,Long>{
}
