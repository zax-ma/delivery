package com.study.delivery.shop.strategy;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DiscountFactory {
    private final Map<String, IDiscountStrategy> strategies;

    public DiscountFactory(Map<String, IDiscountStrategy> strategies) {
        this.strategies = strategies;
    }

    public IDiscountStrategy getStrategy(String type){
        IDiscountStrategy strategy = strategies.get(type);
        if(strategy == null){
            return (userGoods, discount) -> userGoods;
        }
        return strategy;
    }
}
