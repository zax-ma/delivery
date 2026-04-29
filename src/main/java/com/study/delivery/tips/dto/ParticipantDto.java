package com.study.delivery.tips.dto;

public record ParticipantDto(String name,
        String type,
        String description,
        boolean isLoyaltyParticipant,
        double percentage) {
}
