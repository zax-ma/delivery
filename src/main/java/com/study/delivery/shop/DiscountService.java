package com.study.delivery.shop;

import com.study.delivery.shop.strategy.DiscountFactory;
import com.study.delivery.shop.strategy.IDiscountStrategy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DiscountService {
    private final UserDiscountService userDiscountService;
    private final DiscountFactory discountFactory;

    public DiscountService(UserDiscountService userDiscountService, DiscountFactory discountFactory) {
        this.userDiscountService = userDiscountService;
        this.discountFactory = discountFactory;
    }

    public UserCart applyDiscount(UUID userId, UserCart cart) {
        List<UserGoods> tmpGoods = cart.getUserGoodsInCart();
        Discount discount = userDiscountService.getPersonalDiscount(userId);
        String discountType = discount.getType();

        IDiscountStrategy strategy = discountFactory.getStrategy(discountType);
        List<UserGoods> resultGoods = strategy.applyDiscount(tmpGoods, discount.getRate());

        return new UserCart(userId, resultGoods);
    }
}
