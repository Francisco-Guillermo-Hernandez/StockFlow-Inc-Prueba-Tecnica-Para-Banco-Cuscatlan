package dev.francisco_hernandez.prueba_tecnica.dto;

import jakarta.persistence.*;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {


    @NotBlank(message = "SKU is required")
    @Size(max = 100, message = "SKU debe de contener menos de 100 caracteres.")
    @Pattern(regexp = "^[a-zA-Z0-9\\-_]+$", message = "SKU puede contener")
    private String sku;

    @NotBlank(message = "El Nombre es requerido")
    @Size(max = 20, message = " El nombre debe de tener menos de 20 caracteres ")
    private String name;

    @Size(max = 100, message = "El Resumen debe de contener menos de 250 caracteres.")
    private String description;

    @NotNull(message = "El Stock es requerido")
    @Min(value = 0, message = "El Stock debe de ser mayor a 0")
    private Integer currentStock;


    @NotNull(message = "La cantidad de productos mininima es requerida")
    @Min(value = 0, message = "El Stock debe de ser mayor a 0")
    private Integer minStock;


    @NotNull(message = "El precio es requerido Precio")
    @DecimalMin(value = "0.01", message = "El precio debe de ser mayour a 0")
    @Digits(integer = 10, fraction = 2, message = "Precio debe de contener numeros y decimales")
    private Float unitPrice;


    @DecimalMin(value = "0.001", message = "El peso debe de ser mayour a 0")
    @Digits(integer = 8, fraction = 3, message = "Peso ...")
    private BigDecimal weight;


    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}