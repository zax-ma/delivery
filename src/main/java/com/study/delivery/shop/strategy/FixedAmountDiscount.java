package com.study.delivery.shop.strategy;

import com.study.delivery.shop.UserGoods;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("FIXED")
public class FixedAmountDiscount implements IDiscountStrategy{

    @Override
    public List<UserGoods> applyDiscount(List<UserGoods> userGoods, double discount) {
        double totalSum = userGoods.stream()
                .mapToDouble(UserGoods::getPrice)
                .filter(price -> price > 0)
                .sum();

        return userGoods.stream()
                .map(p -> {
                    double newPrice = (totalSum > 0 && p.getPrice() > 0)
                            ? fixedDiscountCounter(totalSum, p.getPrice(), discount)
                            : 0;
                    double totalPrice = p.getPrice() - newPrice;
                    return new UserGoods(p.getId(), p.getPrice(), totalPrice);
                }).toList();
    }

    private double fixedDiscountCounter(double amount, double price, double discount){
        return (price / amount) * discount;
    }
}
