package dev.francisco_hernandez.prueba_tecnica.mapper;

import dev.francisco_hernandez.prueba_tecnica.entities.Product;
import dev.francisco_hernandez.prueba_tecnica.dto.ProductDto;

import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface ProductMapper {

    @org.mapstruct.Mapping(target = "id", ignore = true)
    Product toEntity(ProductDto dto);

    ProductDto toDto(Product entity);

}