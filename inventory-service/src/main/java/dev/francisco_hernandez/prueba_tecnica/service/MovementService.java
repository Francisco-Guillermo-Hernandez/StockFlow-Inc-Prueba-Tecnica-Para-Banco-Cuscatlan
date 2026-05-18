package dev.francisco_hernandez.prueba_tecnica.service;

import dev.francisco_hernandez.prueba_tecnica.entities.Movement;
import dev.francisco_hernandez.prueba_tecnica.exceptions.MethodArgumentNotValidException;
import dev.francisco_hernandez.prueba_tecnica.exceptions.ResourceNotFoundException;
import dev.francisco_hernandez.prueba_tecnica.exceptions.ServerErrorException;
import dev.francisco_hernandez.prueba_tecnica.repository.MovementRepository;
import dev.francisco_hernandez.prueba_tecnica.repository.ProductRepository;
import dev.francisco_hernandez.prueba_tecnica.service.interfaces.IMovementService;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementService implements IMovementService {

    ProductService productService;
    MovementRepository repository;
    public MovementService(MovementRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    @Override
    public List<Movement> listAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new ServerErrorException("Error listing all movement entities");
        }
    }

    @Override
    public List<Movement> listAllByProductId(Long productId) {
        try {
            List<Movement> movements = repository.findByProductId(productId);

            if (movements.isEmpty()) {
                throw new ResourceNotFoundException("No se encuentran los movimientos con id: " + productId);
            }

            return movements;
        } catch (DataAccessException e) {
            throw new MethodArgumentNotValidException("Error al obtener el producto con id: " + e.getMessage());
        } catch (Exception ex) {
            throw new ServerErrorException("Error interno: " +ex.getMessage());
        }
    }

    @Override
    public Movement createAMovement(Movement movement) {
        try {
            movement.setTimestamp(java.time.LocalDateTime.now());

            productService.updateStock(movement.getProductId(), movement.getQuantity());

            return repository.save(movement);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new MethodArgumentNotValidException("Error al crear el movimiento");
        }
    }
}
