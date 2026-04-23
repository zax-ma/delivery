package com.study.delivery.services;

import com.study.delivery.AllDao;
import com.study.delivery.models.Delivery;
import com.study.delivery.models.Participant;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class ParticipantImpl implements IParticipant {

   // private final ParticipantRepository repository;
    AllDao dao;
    List<Participant> participantList;
    List<Delivery> deliveryEntities;

    public ParticipantImpl() {
        this.dao = new AllDao();
        this.participantList = dao.createParticipantData();
        this.deliveryEntities = dao.createDeliveryData();
    }

    @Override
    public Participant getParticipantById(Long id) {
        for (Participant participant : participantList) {
            if (id != null && id.equals(participant.getId())) {
                return participant;
            }
        }
        throw new RuntimeException("Участник не найден");
    }
    //TODO вынести в отдельный сервис
    public Delivery getDeliveryById(Long id){
        for (Delivery delivery : deliveryEntities) {
            if (id != null && id.equals(delivery.getId())) {
                return delivery;
            }
        }
        throw new RuntimeException("Доставка не найдена");
    }
}
