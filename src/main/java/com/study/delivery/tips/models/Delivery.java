package com.study.delivery.tips.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@AllArgsConstructor
@Getter
public class Delivery {

    long id;
    long orderId;
    long courierId;
    long restaurantId;
    boolean isHardDelivery;

}
