package com.study.delivery.shop.strategy;

import com.study.delivery.shop.UserGoods;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("NONE")
public class NoneDiscount implements IDiscountStrategy {
    @Override
    public List<UserGoods> applyDiscount(List<UserGoods> userGoods, double discount) {
        return userGoods;
    }
}
