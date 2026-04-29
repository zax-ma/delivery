package com.study.delivery.shop;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCart {
    UUID userId;
    List<UserGoods> userGoodsInCart;

    @Override
    public String toString() {
        return "UserCart{" +
                "userId=" + userId +
                ", userGoodsInCart=" + userGoodsInCart +
                '}';
    }
}
