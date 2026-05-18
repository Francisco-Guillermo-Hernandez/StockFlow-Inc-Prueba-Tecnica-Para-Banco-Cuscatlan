package dev.francisco_hernandez.prueba_tecnica.repository;

import dev.francisco_hernandez.prueba_tecnica.entities.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<Movement, Long> { }
