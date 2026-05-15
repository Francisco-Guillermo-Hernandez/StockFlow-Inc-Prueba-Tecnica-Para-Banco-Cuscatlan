package dev.francisco_hernandez.prueba_tecnica.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movements")
class MovementController {

    @PostMapping
    public String register() {
        return "hello world";
    }

    @GetMapping("{productId}/history")
    public String history(@PathVariable String productId) {
        return "hello world";
    }
}
