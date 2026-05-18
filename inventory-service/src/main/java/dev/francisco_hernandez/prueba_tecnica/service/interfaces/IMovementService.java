package dev.francisco_hernandez.prueba_tecnica.service.interfaces;

import dev.francisco_hernandez.prueba_tecnica.entities.Movement;

import java.util.List;

public interface IMovementService {

    List<Movement> listAll();

    List<Movement> listAllByProductId(Long productId);
    Movement createAMovement(Movement movement);
}
