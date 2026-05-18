package dev.francisco_hernandez.prueba_tecnica.dto;


import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MovementDto {

    @NotNull(message = "El Id del producto es requerido")
    private Long productId;

    @NotNull(message = "La cantidad es rrequerida")
    @Min(value = 0, message = "La cantidad a mover debe de ser mayor a 0")
    private Integer quantity;

   @NotBlank(message = "Por favor ingrese una razon por la cual se hace el movimento")
   @Size(max = 100, min = 10)
   @Pattern(regexp = "^[a-zA-Z0-9\\-_]+$", message = "SKU puede contener")
    private String reason;
}
