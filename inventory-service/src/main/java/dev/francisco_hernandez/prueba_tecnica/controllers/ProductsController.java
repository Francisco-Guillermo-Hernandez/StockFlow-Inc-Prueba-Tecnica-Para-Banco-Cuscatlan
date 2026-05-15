package dev.francisco_hernandez.prueba_tecnica.controllers;

import dev.francisco_hernandez.prueba_tecnica.entities.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
class ProductsController {

    @GetMapping
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        return products;
    }

    @GetMapping("/{id}")
    public String productById(@PathVariable String id) {
        return  "hello world";
    }

    @PostMapping
    public String addProduct(@RequestBody Product product) {
        return "hello world";
    }
}
