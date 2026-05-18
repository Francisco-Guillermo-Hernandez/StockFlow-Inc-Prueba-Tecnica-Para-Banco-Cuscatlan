package dev.francisco_hernandez.prueba_tecnica.dto;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AlertDto {

    @NotBlank(message = "El nombre es requerido")
    private String productName;

    @Size(min = 1)
    @NotBlank(message = "La cantidad es requerida")
    private Integer currentStock;

    @NotBlank(message = "La cantidad es requerida")
    @Size(min = 1)
    private Integer minStock;

    @NotBlank(message = "La severidad requerida")
    private String severity;
}
