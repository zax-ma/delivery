package com.study.delivery.services;

import com.study.delivery.models.Delivery;
import com.study.delivery.models.Participant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.study.delivery.constants.ApplicationConstants.DEFAULT_FEE;
import static com.study.delivery.constants.ApplicationConstants.PLATFORM;

/**
 * Сервис распределения чаевых.
 *
 * Правила округления: в копейках, округление вниз для платформы и ресторана,
 * остаток — курьеру (сумма долей должна равняться исходным чаевым).
 */
@Service
public class TipDistributionServiceImpl implements ITipDistributionService {
    private final ParticipantImpl serviceP;
    @Value("${tips.platform.fee}")
    long platformFee;
    @Value("${tips.platform.bonus.courier}")
    long courierPercentBonus;

    public TipDistributionServiceImpl(ParticipantImpl serviceP) {
        this.serviceP = serviceP;
    }

    @Override
    public DistributionResult distribute(long tipAmount, Delivery delivery) {
        if (tipAmount < 0L) {
            throw new IllegalArgumentException("Сумма чаевых не может быть отрицательной");
        }
        if (tipAmount == 0L) {
            return new DistributionResult(Map.of(
                    PLATFORM, 0L,
                    delivery.getCourierId(), 0L,
                    delivery.getRestaurantId(), 0L));
        }
        Map<Long, Long> map = new HashMap<>();
        long platformTip = tipCalculator(tipAmount, platformFee);

        Participant rawRestaurant = serviceP.getParticipantById(delivery.getRestaurantId());
        Map<Long, Long> rawRestaurantMap = new HashMap<>();
        rawRestaurantMap.put(rawRestaurant.getId(), rawRestaurant.getPercentage());
        boolean isProgramMember = rawRestaurant.isLoyaltyParticipant();
        long restaurantTip = DEFAULT_FEE;
        if(!isProgramMember){
            map.put(rawRestaurantMap.keySet().iterator().next(), restaurantTip);
        } else {
            restaurantTip = tipCalculator(tipAmount, rawRestaurantMap.values().iterator().next());
            map.put(rawRestaurantMap.keySet().iterator().next(), restaurantTip);
        }

        boolean isHardDelivery = serviceP.getDeliveryById(delivery.getId()).isHardDelivery();
        long courierTip;
        if(!isHardDelivery) {
            courierTip = tipAmount - (platformTip + restaurantTip);
        } else {
            long tipBonus = tipCalculator(platformTip, courierPercentBonus);
            courierTip = tipAmount - (platformTip + restaurantTip) + tipBonus;
            platformTip -= tipBonus;
        }
        map.put(delivery.getCourierId(), courierTip);
        map.put(PLATFORM, platformTip);

        return new DistributionResult(map);
    }

    private long tipCalculator(long amount, long percentage){
        return (amount * percentage) / 100;
    }
}
