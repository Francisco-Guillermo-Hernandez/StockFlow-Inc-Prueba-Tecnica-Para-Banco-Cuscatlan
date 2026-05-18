package dev.francisco_hernandez.prueba_tecnica.controllers;

import dev.francisco_hernandez.prueba_tecnica.dto.MovementDto;
import dev.francisco_hernandez.prueba_tecnica.entities.Movement;
import dev.francisco_hernandez.prueba_tecnica.mapper.MovementMapper;
import dev.francisco_hernandez.prueba_tecnica.service.MovementService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/movements")
public class MovementController {

    private final MovementService service;
    private final MovementMapper movementMapper;

    public MovementController(MovementService service, MovementMapper movementMapper) {
        this.service = service;
        this.movementMapper = movementMapper;
    }

    @PostMapping("/")
    public Movement register(@Valid @RequestBody MovementDto movementDto) {
        Movement movement = movementMapper.toEntity(movementDto);
        return service.createAMovement(movement);
    }

    @GetMapping("/{productId}/history")
    public List<Movement> history(@PathVariable Long productId) {
       return service.listAllByProductId(productId);
    }


    @GetMapping("/")
    public List<Movement> listMovements() {
        return service.listAll();
    }

}
