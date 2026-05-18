package dev.francisco_hernandez.prueba_tecnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.francisco_hernandez.prueba_tecnica.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> { }
