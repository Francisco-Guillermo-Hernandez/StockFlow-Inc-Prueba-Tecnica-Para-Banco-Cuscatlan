package dev.francisco_hernandez.prueba_tecnica.service;

import dev.francisco_hernandez.prueba_tecnica.exceptions.MethodArgumentNotValidException;
import dev.francisco_hernandez.prueba_tecnica.exceptions.ResourceNotFoundException;
import dev.francisco_hernandez.prueba_tecnica.exceptions.ServerErrorException;
import dev.francisco_hernandez.prueba_tecnica.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import dev.francisco_hernandez.prueba_tecnica.entities.Product;
import dev.francisco_hernandez.prueba_tecnica.service.interfaces.*;

@Service
public class ProductService implements IProductService  {

    private final ProductRepository repository;
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product createProduct(Product product) {
        try {
            java.time.LocalDateTime now = java.time.LocalDateTime.now();
            product.setCreatedAt(now);
            product.setUpdatedAt(now);
            return repository.save(product);
        } catch (Exception ex) {
            throw new MethodArgumentNotValidException("Error al crear producto: " + ex.getMessage());
        }
    }

    @Override
    public List<Product> listAll() {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            throw new MethodArgumentNotValidException("Error al listar productos" + ex.getMessage());
        }
    }

    @Override
    public Product getProductById(Long id) {

        try {
            return repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("No se encuentra el producto con id: " + id));
        } catch (DataAccessException e) {
            throw new MethodArgumentNotValidException("Error al obtener el producto con id: " + id);
        } catch (Exception ex) {
            throw new ServerErrorException("Error interno: " +ex.getMessage());
        }
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        try {

            Optional<Product> existingProduct = repository.findById(id);

            if (existingProduct.isPresent()) {
                Product productToUpdate = existingProduct.get();


                // Detalles
                productToUpdate.setName(product.getName());
                productToUpdate.setDescription(product.getDescription());

                //
                productToUpdate.setUnitPrice(product.getUnitPrice());
                productToUpdate.setWeight(product.getWeight());

                productToUpdate.setSku(product.getSku());
                // Stock
                productToUpdate.setMinStock(product.getMinStock());
                productToUpdate.setCurrentStock(product.getCurrentStock());

                //
                productToUpdate.setActive(product.getActive());

                productToUpdate.setUpdatedAt(java.time.LocalDateTime.now());

                return repository.save(productToUpdate);
            } else {
                throw new EntityNotFoundException("El product con id " + id + " no fue encontrado");
            }
        } catch (Exception ex) {
            throw new MethodArgumentNotValidException("Error al actualizar producto: " + ex.getMessage());
        }
    }

    @Override
    public Product updateStock(Long id, int newStock) {
        try {
            Optional<Product> existingProduct = repository.findById(id);

            if (existingProduct.isPresent()) {
                Product productToUpdate = existingProduct.get();
                productToUpdate.setCurrentStock(productToUpdate.getCurrentStock() + newStock);
                return repository.save(productToUpdate);
            } else {
                throw new EntityNotFoundException("El producto con id " + id + " no existe");
            }

        } catch (Exception ex) {
            throw new MethodArgumentNotValidException("Error al actualizar producto: " + ex.getMessage());
        }
    }

}