package com.study.delivery.shop.strategy;

import com.study.delivery.shop.UserGoods;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("PERCENT")
public class PercentOfAmountDiscount implements IDiscountStrategy{

    @Override
    public List<UserGoods> applyDiscount(List<UserGoods> userGoods, double discount) {
        return userGoods.stream()
                .map(p -> {
                    double userDiscount = (p.getPrice() > 0)
                                        ? percentDiscountCounter(p.getPrice(), discount)
                                        : 0;
                    double totalPriceResult = p.getPrice() - userDiscount;
                    return new UserGoods(p.getId(), p.getPrice(), totalPriceResult);
                }).toList();
    }

    private double percentDiscountCounter(double price, double percent){
        return (price * percent) / 100;
    }
}
