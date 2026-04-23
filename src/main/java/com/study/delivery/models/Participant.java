package com.study.delivery.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class Participant {

    long Id;
    String name;
    String type;
    String description;
    boolean isLoyaltyParticipant;
    long percentage;

    public Participant(long id, String name, String type, String description, boolean isLoyaltyParticipant, long percentage) {
        Id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.isLoyaltyParticipant = isLoyaltyParticipant;
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Id == that.Id && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name);
    }

    @Override
    public String toString() {
        return "Participant{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", isLoyaltyParticipant=" + isLoyaltyParticipant +
                ", percentage=" + percentage +
                '}';
    }
}
