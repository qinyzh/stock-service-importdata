package com.fsd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.fsd.repository.StockPriceDetailRepository;
import com.fsd.service.StockPriceDetailService;

public class StockPriceDetailServiceImpl implements StockPriceDetailService{
    @Autowired
    private StockPriceDetailRepository stockPriceDetailRepository;
}