package com.study.delivery.tips.mapper;


import com.study.delivery.tips.dto.ParticipantDto;
import com.study.delivery.tips.models.Participant;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface ParticipantMapper {
    @Mapping(target = "id", ignore = true)
    Participant fromDtoToEntity(ParticipantDto requestDto);

    @Mapping(target = "id", ignore = true)
    ParticipantDto fromEntityToDto(Participant entity);
}
