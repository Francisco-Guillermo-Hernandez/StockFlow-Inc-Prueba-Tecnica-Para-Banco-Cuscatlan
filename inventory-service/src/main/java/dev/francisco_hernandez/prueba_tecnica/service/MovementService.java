package dev.francisco_hernandez.prueba_tecnica.service;

import dev.francisco_hernandez.prueba_tecnica.entities.Alert;
import dev.francisco_hernandez.prueba_tecnica.entities.Movement;
import dev.francisco_hernandez.prueba_tecnica.entities.Product;
import dev.francisco_hernandez.prueba_tecnica.exceptions.InsufficientStockException;
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
import java.util.Objects;

@Service
public class MovementService implements IMovementService {

    ProductService productService;
    MovementRepository repository;
    AlertService alertService;
    public MovementService(MovementRepository repository, ProductService productService, AlertService alertService) {
        this.repository = repository;
        this.productService = productService;
        this.alertService = alertService;
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
            // Validate product existence first
            productService.getProductById(productId);

            List<Movement> movements = repository.findByProductId(productId);

            if (movements.isEmpty()) {
                throw new ResourceNotFoundException("No se encontraron movimientos para el producto con id: " + productId);
            }

            return movements;
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (DataAccessException e) {
            throw new MethodArgumentNotValidException("Error de base de datos al obtener movimientos: " + e.getMessage());
        } catch (Exception ex) {
            throw new ServerErrorException("Error interno: " + ex.getMessage());
        }
    }

    @Override
    public Movement createAMovement(Movement movement) throws InsufficientStockException {
        try {
            movement.setTimestamp(java.time.LocalDateTime.now());

           Product product =  productService.updateStock(movement.getProductId(), movement.getQuantity());

            Alert alert = new Alert();
            alert.setMinStock(product.getMinStock());
            alert.setCurrentStock(product.getCurrentStock());
            alert.setProductName(product.getName());


            if (product.getCurrentStock() < product.getMinStock()) {
                alert.setSeverity("Peligro: Los productos se estan agotando");
            }

            if (Objects.equals(product.getCurrentStock(), product.getMinStock())) {
                alert.setSeverity("Advertencia: Revise el inventario");
            }

            if (product.getCurrentStock() > product.getMinStock()) {
                alert.setSeverity("Normal");
            }


            if (product.getCurrentStock() == 0 || product.getCurrentStock() < 0) {
                throw new InsufficientStockException(product.getName());
            }

            alertService.save(alert);

            return repository.save(movement);
        } catch (InsufficientStockException e) {
          throw e;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            throw new MethodArgumentNotValidException("Error al crear el movimiento");
        }
    }
}
