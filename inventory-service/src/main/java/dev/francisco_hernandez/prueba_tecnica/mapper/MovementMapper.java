package dev.francisco_hernandez.prueba_tecnica.mapper;

import dev.francisco_hernandez.prueba_tecnica.entities.Movement;
import dev.francisco_hernandez.prueba_tecnica.dto.MovementDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovementMapper {

    @org.mapstruct.Mapping(target = "id", ignore = true)
    Movement toEntity(MovementDto dto);

    MovementDto toDto(Movement entity);
}
