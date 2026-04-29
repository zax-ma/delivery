package com.study.delivery.shop.strategy;

import com.study.delivery.shop.UserGoods;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("FIXED")
public class FixedAmountDiscount implements IDiscountStrategy{

    @Override
    public List<UserGoods> applyDiscount(List<UserGoods> userGoods, double discount) {
        List<UserGoods> goodsResult = new ArrayList<>();
        double totalAmount = 0.0;
        for (UserGoods goods : userGoods){
            totalAmount += goods.getPrice();
        }
        for (UserGoods goods : userGoods) {
            double oldPrice = goods.getPrice();
            double newPrice = fixedDiscountCounter(totalAmount, oldPrice, discount);
            double totalPriceResult = oldPrice - newPrice;
            goodsResult.add(new UserGoods(goods.getId(), goods.getPrice(), totalPriceResult));
        }
        return goodsResult;
    }

    private double fixedDiscountCounter(double amount, double price, double discount){
        return (price / amount) * discount;
    }
}
