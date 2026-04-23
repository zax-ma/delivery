package com.study.delivery.mapper;

import com.study.delivery.dto.DeliveryDto;
import com.study.delivery.models.Delivery;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface DeliveryMapper {
    @Mapping(target = "id", ignore = true)
    Delivery fromDtoToEntity(DeliveryDto requestDto);

    @Mapping(target = "id", ignore = true)
    DeliveryDto fromEntityToDto(Delivery entity);
}
