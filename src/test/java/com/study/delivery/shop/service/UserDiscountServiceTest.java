package com.study.delivery.shop.service;

import com.study.delivery.shop.*;
import com.study.delivery.shop.strategy.DiscountFactory;
import com.study.delivery.shop.strategy.IDiscountStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDiscountServiceTest {

    @Mock
    private UserDiscountService service;

    @Mock
    private DiscountFactory discountFactory;

    @Mock
    private IDiscountStrategy discountStrategy;

    @InjectMocks
    private DiscountService discountService;

    private UUID userId;
    private UserCart cart;
    private Discount discount;

    @BeforeEach
    void setUp(){
        userId = UUID.fromString("4ace4830-d0e9-4a8d-a956-1b5caf1a168b");
        List<UserGoods> testGoods = new ArrayList<>();
        testGoods.add(new UserGoods(UUID.randomUUID(), 20.0, 20.0));
        testGoods.add(new UserGoods(UUID.randomUUID(), 30.0, 30.0));
        cart = new UserCart(userId, testGoods);
        discount = new Discount();
    }

    @Test
    void shouldApplyDiscountInPercent() {
        discount.setType("PERCENT");
        discount.setRate(10.0);

        List<UserGoods> mockResult = List.of(
                new UserGoods(UUID.randomUUID(), 20.0, 18.0),
                new UserGoods(UUID.randomUUID(), 30.0, 27.0)
        );

        when(service.getPersonalDiscount(userId)).thenReturn(discount);
        when(discountFactory.getStrategy("PERCENT")).thenReturn(discountStrategy);

        when(discountStrategy.applyDiscount(anyList(), eq(10.0))).thenReturn(mockResult);

        UserCart result = discountService.applyDiscount(userId, cart);

        assertNotNull(result);
        assertEquals(mockResult, result.getUserGoodsInCart());

        verify(discountFactory).getStrategy("PERCENT");
        verify(discountStrategy).applyDiscount(anyList(), eq(10.0));
    }


    @Test
    void shouldApplyFixedDiscount(){
        discount.setType("FIXED");
        discount.setRate(10.0);

        List<UserGoods> mockResult = List.of(
                new UserGoods(UUID.randomUUID(), 20.0, 16.0),
                new UserGoods(UUID.randomUUID(), 30.0, 24.0)
        );

        when(service.getPersonalDiscount(userId)).thenReturn(discount);
        when(discountFactory.getStrategy("FIXED")).thenReturn(discountStrategy);

        when(discountStrategy.applyDiscount(anyList(), eq(10.0))).thenReturn(mockResult);

        UserCart result = discountService.applyDiscount(userId, cart);

        assertNotNull(result);
        assertEquals(mockResult, result.getUserGoodsInCart());

        verify(discountFactory).getStrategy("FIXED");
        verify(discountStrategy).applyDiscount(anyList(), eq(10.0));
    }

    @Test
    void shouldApplyNoDiscount() {
        discount.setType("NONE");
        discount.setRate(0.0);

        when(service.getPersonalDiscount(userId)).thenReturn(discount);
        when(discountFactory.getStrategy("NONE")).thenReturn(discountStrategy);
        when(discountStrategy.applyDiscount(anyList(), anyDouble()))
                .thenReturn(cart.getUserGoodsInCart());

        UserCart result = discountService.applyDiscount(userId, cart);

        assertSame(cart.getUserGoodsInCart(), result.getUserGoodsInCart());
        verify(discountStrategy).applyDiscount(eq(cart.getUserGoodsInCart()), eq(0.0));
    }
}
