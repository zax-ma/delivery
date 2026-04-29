package com.study.delivery.shop;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserGoods {

    UUID id;
    double price;
    double priceWithDiscount;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserGoods userGoods = (UserGoods) o;
        return Double.compare(price, userGoods.price) == 0 && Double.compare(priceWithDiscount, userGoods.priceWithDiscount) == 0 && Objects.equals(id, userGoods.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, priceWithDiscount);
    }

    @Override
    public String toString() {
        return "UserGoods{" +
                "id=" + id +
                ", price=" + price +
                ", priceWithDiscount=" + priceWithDiscount +
                '}';
    }
}
