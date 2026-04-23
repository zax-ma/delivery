package com.study.delivery.services;

import com.study.delivery.models.Delivery;

public interface ITipDistributionService {
    /**
     * Распределить чаевые между участниками.
     *
     * @param tipAmount сумма чаевых в копейках
     * @param delivery информация о доставке
     * @return результат распределения
     */
    DistributionResult distribute(long tipAmount, Delivery delivery);
}
