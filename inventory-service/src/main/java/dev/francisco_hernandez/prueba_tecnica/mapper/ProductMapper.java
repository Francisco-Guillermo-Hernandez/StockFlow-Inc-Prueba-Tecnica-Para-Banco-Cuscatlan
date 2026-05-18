package dev.francisco_hernandez.prueba_tecnica.mapper;

import dev.francisco_hernandez.prueba_tecnica.entities.Product;
import dev.francisco_hernandez.prueba_tecnica.dto.ProductDto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface ProductMapper {

    @org.mapstruct.Mapping(target = "id", ignore = true)
    @org.mapstruct.Mapping(target = "createdAt", ignore = true)
    @org.mapstruct.Mapping(target = "updatedAt", ignore = true)
    Product toEntity(ProductDto dto);

    ProductDto toDto(Product entity);

}