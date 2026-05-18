package dev.francisco_hernandez.prueba_tecnica.exceptions;

public class MethodArgumentNotValidException extends RuntimeException  {
    public MethodArgumentNotValidException(String message) {
        super(message);
    }
}
