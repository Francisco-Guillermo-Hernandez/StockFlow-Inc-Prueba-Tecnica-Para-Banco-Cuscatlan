package dev.francisco_hernandez.prueba_tecnica.exceptions;

public class ServerErrorException extends RuntimeException  {
    public ServerErrorException(String message) {
        super(message);
    }
}

