package dev.francisco_hernandez.prueba_tecnica.controllers;

import dev.francisco_hernandez.prueba_tecnica.entities.Product;
import dev.francisco_hernandez.prueba_tecnica.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import  dev.francisco_hernandez.prueba_tecnica.mapper.ProductMapper;
import java.util.List;
import dev.francisco_hernandez.prueba_tecnica.dto.ProductDto;

@RestController
@RequestMapping("/products")
//@Tag(name = "Books", description = "Operations for managing books")
public class ProductsController {

    private final ProductMapper productMapper;
    private final ProductService service;

    public ProductsController(ProductMapper productMapper, ProductService service) {
        this.productMapper = productMapper;
        this.service = service;
    }

    @Operation(summary = "Listar todos los Productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de productos"),
            @ApiResponse(responseCode = "404", description = "No hay productos")
    })
    @GetMapping("/")
    public List<Product> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public Product productById(@PathVariable String id) {
        return service.getProductById(Long.parseLong(id));
    }

    @PostMapping("/")
    public Product createProduct(@RequestBody ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        return service.createProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(
        @PathVariable String id,
        @RequestBody ProductDto productDto
    ) {
        Product product = productMapper.toEntity(productDto);
        return service.updateProduct(Long.parseLong(id), product);
    }
}
