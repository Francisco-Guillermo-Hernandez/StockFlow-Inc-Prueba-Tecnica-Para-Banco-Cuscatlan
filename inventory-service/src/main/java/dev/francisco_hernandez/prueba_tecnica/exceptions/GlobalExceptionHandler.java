package dev.francisco_hernandez.prueba_tecnica.exceptions;

import dev.francisco_hernandez.prueba_tecnica.entities.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(MethodArgumentNotValidException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage() == null ? "" : ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadFormatException.class)
    public ResponseEntity<ErrorResponse> handleNumberFormatException(NumberFormatException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage() == null ? "" : ex.getMessage(),
                400,
                System.currentTimeMillis()
        );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleServerErrorException(ServerErrorException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage() == null ? "" : ex.getMessage(),
                500,
                System.currentTimeMillis()
        );
        return ResponseEntity.internalServerError().body(error);
    }
}