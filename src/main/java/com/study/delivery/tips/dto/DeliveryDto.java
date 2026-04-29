package com.study.delivery.tips.dto;

public record DeliveryDto(long orderId,
                          long courierId,
                          long restaurantId,
                          boolean isHardDelivery) {
}
