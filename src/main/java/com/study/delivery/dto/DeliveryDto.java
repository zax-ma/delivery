package com.study.delivery.dto;

public record DeliveryDto(long orderId,
                          long courierId,
                          long restaurantId,
                          boolean isHardDelivery) {
}
