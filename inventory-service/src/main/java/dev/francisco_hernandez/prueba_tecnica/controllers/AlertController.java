package dev.francisco_hernandez.prueba_tecnica.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    @GetMapping("/")
    public String listAlerts() {
        return "hello world";
    }

    @GetMapping("/events")
    public String events() {
        return "hello world";
    }
}
