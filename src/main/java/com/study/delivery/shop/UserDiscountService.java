package com.study.delivery.shop;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserDiscountService implements IUserDiscountService {
    Discount discount;

    @Override
    public Discount getPersonalDiscount(UUID userId) {
        return discount;
    }
}
