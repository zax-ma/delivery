package com.study.delivery.shop.strategy;
import com.study.delivery.shop.UserGoods;
import java.util.List;

public interface IDiscountStrategy {
    List<UserGoods> applyDiscount(List<UserGoods> userGoods, double discount);
}
