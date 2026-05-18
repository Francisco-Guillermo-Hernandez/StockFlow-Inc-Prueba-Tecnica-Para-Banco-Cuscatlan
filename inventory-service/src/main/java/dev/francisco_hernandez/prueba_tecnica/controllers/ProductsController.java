package dev.francisco_hernandez.prueba_tecnica.controllers;

import dev.francisco_hernandez.prueba_tecnica.entities.Product;
import dev.francisco_hernandez.prueba_tecnica.exceptions.BadFormatException;
import dev.francisco_hernandez.prueba_tecnica.exceptions.BadRequestException;
import dev.francisco_hernandez.prueba_tecnica.exceptions.ResourceNotFoundException;
import dev.francisco_hernandez.prueba_tecnica.service.ProductService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;
import dev.francisco_hernandez.prueba_tecnica.configuration.Constants;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
class ProductsController {

    private final ProductService service;
    public ProductsController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public Product productById(@PathVariable String id) {
        return service.getProductById(Long.parseLong(id));
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
       return service.createProduct(product);
    }
}
