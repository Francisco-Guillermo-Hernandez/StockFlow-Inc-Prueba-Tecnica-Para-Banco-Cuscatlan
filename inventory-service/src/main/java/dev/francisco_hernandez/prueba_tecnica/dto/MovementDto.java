package dev.francisco_hernandez.prueba_tecnica.dto;


import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementDto {

    @NotNull(message = "El Id del producto es requerido")
    private Long productId;

    @NotNull(message = "La cantidad es requerida")
    private Integer quantity;

   @NotBlank(message = "Por favor ingrese una razon por la cual se hace el movimento")
   @Size(max = 100, min = 10)
    private String reason;


   @NotBlank(message = "Se require el nombre de la operacion")
   private String operationName;

}
