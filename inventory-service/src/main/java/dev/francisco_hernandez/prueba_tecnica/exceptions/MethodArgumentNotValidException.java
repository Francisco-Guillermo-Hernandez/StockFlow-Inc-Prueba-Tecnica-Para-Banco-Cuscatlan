package dev.francisco_hernandez.prueba_tecnica.exceptions;

public class BadRequestException extends RuntimeException  {
    public BadRequestException(String message) {
        super(message);
    }
}
