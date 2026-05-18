package dev.francisco_hernandez.prueba_tecnica.mapper;

import dev.francisco_hernandez.prueba_tecnica.entities.Movement;
import dev.francisco_hernandez.prueba_tecnica.dto.MovementDto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface MovementMapper {

//    @org.mapstruct.Mapping(target = "id", ignore = true)
//    @org.mapstruct.Mapping(target = "timestamp", ignore = true)
    Movement toEntity(MovementDto dto);

    MovementDto toDto(Movement entity);
}
