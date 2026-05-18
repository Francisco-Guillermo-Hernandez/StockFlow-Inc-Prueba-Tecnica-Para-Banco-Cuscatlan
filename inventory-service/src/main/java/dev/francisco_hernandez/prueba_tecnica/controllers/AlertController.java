package dev.francisco_hernandez.prueba_tecnica.controllers;

import dev.francisco_hernandez.prueba_tecnica.entities.Alert;
import dev.francisco_hernandez.prueba_tecnica.service.AlertService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    private final AlertService service;
    public AlertController(AlertService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Alert> listAlerts() {
        return service.findAll();
    }

    @GetMapping("/events")
    public String events() {
        return "hello world";
    }
}
