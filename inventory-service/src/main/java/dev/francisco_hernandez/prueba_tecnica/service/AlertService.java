package dev.francisco_hernandez.prueba_tecnica.service;

import dev.francisco_hernandez.prueba_tecnica.entities.Alert;
import dev.francisco_hernandez.prueba_tecnica.exceptions.MethodArgumentNotValidException;
import dev.francisco_hernandez.prueba_tecnica.exceptions.ResourceNotFoundException;
import dev.francisco_hernandez.prueba_tecnica.repository.AlertRepository;
import org.springframework.stereotype.Service;
import dev.francisco_hernandez.prueba_tecnica.service.interfaces.IAlertService;
import java.util.List;


@Service
public class AlertService implements IAlertService {

    private final AlertRepository repository;
    public AlertService(AlertRepository repository) { this.repository = repository; }

    @Override
    public List<Alert> findAll() {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            throw new MethodArgumentNotValidException("Error al listar productos" + ex.getMessage());
        }
    }

    @Override
    public Alert findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra la alerta con id: " + id));
    }

    @Override
    public Alert save(Alert alert) {
        try {
            return repository.save(alert);
        } catch (Exception ex) {
            throw new MethodArgumentNotValidException("Error al save producto" + ex.getMessage());
        }
    }

}
