package dev.francisco_hernandez.prueba_tecnica.service.interfaces;

import dev.francisco_hernandez.prueba_tecnica.entities.Movement;
import dev.francisco_hernandez.prueba_tecnica.exceptions.InsufficientStockException;

import java.util.List;

public interface IMovementService {

    List<Movement> listAll();

    List<Movement> listAllByProductId(Long productId);
    Movement createAMovement(Movement movement) throws InsufficientStockException;
}
