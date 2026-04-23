package com.study.delivery.dto;

public record ParticipantDto(String name,
        String type,
        String description,
        boolean isLoyaltyParticipant,
        double percentage) {
}
