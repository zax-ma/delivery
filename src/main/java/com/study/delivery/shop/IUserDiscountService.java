package com.study.delivery.shop;

import java.util.UUID;

public interface IUserDiscountService {
    Discount getPersonalDiscount(UUID userId);
}
