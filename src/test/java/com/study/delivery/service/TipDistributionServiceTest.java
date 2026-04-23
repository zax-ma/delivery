package com.study.delivery.service;

import com.study.delivery.constants.ApplicationConstants;
import com.study.delivery.models.Delivery;
import com.study.delivery.models.Participant;
import com.study.delivery.services.DistributionResult;
import com.study.delivery.services.ParticipantImpl;
import com.study.delivery.services.TipDistributionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TipDistributionServiceTest {
    @Autowired
    private TipDistributionServiceImpl tipDistributionService;

    @Mock
    private ParticipantImpl participantImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDistribute_WithLoyaltyProgram() {
        long tipAmount = 1000;
        Delivery delivery = new Delivery(333, 345, 8, 7, true);
        Participant restaurant = new Participant(7, "Баффало2","Ресторан", "Получает долю, если участвует в программе лояльности", true, 10); // Участник с программой лояльности
        when(participantImpl.getParticipantById(7L)).thenReturn(restaurant);
        when(participantImpl.getDeliveryById(333L)).thenReturn(delivery);

        DistributionResult result = tipDistributionService.distribute(tipAmount, delivery);

        long platformFee = 10;
        long courierBonus = 15;
        long platformTip = (tipAmount * platformFee) / 100;
        long restaurantTip = (tipAmount * 10) / 100;
        long tipBonus = (platformTip * courierBonus) / 100;
        long courierTip = tipAmount - (platformTip + restaurantTip) + tipBonus;
        platformTip -= tipBonus;
        long totalTips = platformTip + restaurantTip + courierTip;

        assertEquals(platformTip, result.getResult().get(ApplicationConstants.PLATFORM));
        assertEquals(restaurantTip, result.getResult().get(restaurant.getId()));
        assertEquals(courierTip, result.getResult().get(delivery.getCourierId()));

        assertEquals(tipAmount, totalTips);
    }

    @Test
    public void testDistribute_WithoutLoyaltyProgram() {
        long tipAmount = 1000; // 1000 копеек
        Delivery delivery = new Delivery(555, 345, 9, 6, false);

        Participant restaurant = new Participant(6, "Баффало","Ресторан", "Получает долю, если участвует в программе лояльности", false, 10); // Участник без программы лояльности
        when(participantImpl.getParticipantById(6L)).thenReturn(restaurant);
        when(participantImpl.getDeliveryById(555L)).thenReturn(delivery);

        DistributionResult result = tipDistributionService.distribute(tipAmount, delivery);

        long platformTip = (tipAmount * 10) / 100; // Платформа: 5%
        long restaurantTip = ApplicationConstants.DEFAULT_FEE; // Ресторан: 0%
        long courierTip = tipAmount - (platformTip + restaurantTip); // Курьер получает все остальное

        assertEquals(platformTip, result.getResult().get(ApplicationConstants.PLATFORM));
        assertEquals(restaurantTip, result.getResult().get(restaurant.getId()));
        assertEquals(courierTip, result.getResult().get(delivery.getCourierId()));

        double totalTips = platformTip + restaurantTip + courierTip;
        assertEquals(tipAmount, totalTips);
    }

    @Test
    public void testDistribute_NegativeTipAmount() {
        long tipAmount = -100; // Отрицательная сумма чаевых
        Delivery delivery = new Delivery(333, 789, 10, 7, false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tipDistributionService.distribute(tipAmount, delivery);
        });

        assertEquals("Сумма чаевых не может быть отрицательной", exception.getMessage());
    }

    @Test
    public void testDistribute_ZeroTipAmount() {
        long tipAmount = 0; // Нулевая сумма чаевых
        Delivery delivery = new Delivery(444, 112, 11, 5, true); // ID доставки и курьера

        DistributionResult result = tipDistributionService.distribute(tipAmount, delivery);

        assertEquals(0, result.getResult().get(ApplicationConstants.PLATFORM));
        assertEquals(0, result.getResult().get(delivery.getRestaurantId()));
        assertEquals(0, result.getResult().get(delivery.getCourierId()));

        // Проверка сумм
        long totalTips = result.getResult().values().stream().mapToLong(Long::longValue).sum();
        assertEquals(tipAmount, totalTips);
    }
}
