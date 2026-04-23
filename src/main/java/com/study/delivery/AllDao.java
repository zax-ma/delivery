package com.study.delivery;

import com.study.delivery.models.Delivery;
import com.study.delivery.models.Participant;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class AllDao {
    List<Participant> daoParticipant = new ArrayList<>();
    List<Delivery> daoDelivery = new ArrayList<>();

    public List<Participant> createParticipantData(){
        List<Participant> result = new ArrayList<>();
        result.add(new Participant(5, "Баффало","Ресторан", "Получает долю, если участвует в программе лояльности", true, 10));
        result.add(new Participant(6, "Баффало1","Ресторан", "Получает долю, если участвует в программе лояльности", false, 10));
        result.add(new Participant(7, "Баффало2","Ресторан", "Получает долю, если участвует в программе лояльности", true, 10));
        result.add(new Participant(8, "Вася","Курьер", "Всегда получает долю", false, 0));
        result.add(new Participant(9, "Петя","Курьер", "Всегда получает долю", false, 0));
        result.add(new Participant(10, "Коля","Курьер", "Всегда получает долю", false, 0));
        result.add(new Participant(11, "Паша","Курьер", "Всегда получает долю", false, 0));

        return result;
    }

    public List<Delivery> createDeliveryData(){
        List<Delivery> result = new ArrayList<>();
        result.add(new Delivery(111, 123, 8, 5, true));
        result.add(new Delivery(222, 456, 9, 6, true));
        result.add(new Delivery(333, 789, 10, 7, true));
        result.add(new Delivery(444, 112, 11, 5, true));
        result.add(new Delivery(555, 345, 8, 7, false));

        return result;
    }
}
