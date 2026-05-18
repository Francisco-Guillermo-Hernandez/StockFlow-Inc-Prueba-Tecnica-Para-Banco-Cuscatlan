package dev.francisco_hernandez.prueba_tecnica.exceptions;

public class InsufficientStockException extends Exception {

    public InsufficientStockException(String message) {
        super("Stock Insuficiente" + message);
    }
}
