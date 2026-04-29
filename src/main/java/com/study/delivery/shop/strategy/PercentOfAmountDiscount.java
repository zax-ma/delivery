package com.study.delivery.shop.strategy;

import com.study.delivery.shop.UserGoods;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("PERCENT")
public class PercentOfAmountDiscount implements IDiscountStrategy{

    @Override
    public List<UserGoods> applyDiscount(List<UserGoods> userGoods, double discount) {
        List<UserGoods> goodsResult = new ArrayList<>();
        for (UserGoods goods : userGoods) {
            double userDiscount = percentDiscountCounter(goods.getPrice(), discount);
            double oldPrice = goods.getPrice();
            double totalPriceResult = oldPrice - userDiscount;
            goodsResult.add(new UserGoods(goods.getId(), goods.getPrice(), totalPriceResult));
        }
        return goodsResult;
    }

    private double percentDiscountCounter(double price, double percent){
        return (price * percent) / 100;
    }
}
