package dev.francisco_hernandez.prueba_tecnica.mapper;

import dev.francisco_hernandez.prueba_tecnica.entities.Alert;
import dev.francisco_hernandez.prueba_tecnica.dto.AlertDto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface AlertMapper {

    @org.mapstruct.Mapping(target = "id", ignore = true)
    Alert toEntity(AlertDto dto);

    AlertDto toDto(Alert entity);
}
