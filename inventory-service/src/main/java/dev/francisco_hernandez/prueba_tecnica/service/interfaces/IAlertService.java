package dev.francisco_hernandez.prueba_tecnica.service.interfaces;

import java.util.List;
import dev.francisco_hernandez.prueba_tecnica.entities.Alert;

public interface IAlertService {

    List<Alert>  findAll();
    Alert findById(Long id);
    Alert save(Alert alert);
}
