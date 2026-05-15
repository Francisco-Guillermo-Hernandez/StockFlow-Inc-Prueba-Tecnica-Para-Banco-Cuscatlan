package dev.francisco_hernandez.prueba_tecnica.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/products")
class ProductsController {

    @GetMapping("/{id}")
    public String productById(@PathVariable String id) {
        return  "hello world";
    }
}
