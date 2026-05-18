package dev.francisco_hernandez.prueba_tecnica.service.interfaces;

import dev.francisco_hernandez.prueba_tecnica.entities.Product;

import java.util.List;

public interface IProductService {

    Product createProduct(Product product);

    List<Product> listAll();

    Product getProductById(Long id);

    Product updateProduct(Long id, Product product);

    Product updateStock(Long id, int newStock);

}
