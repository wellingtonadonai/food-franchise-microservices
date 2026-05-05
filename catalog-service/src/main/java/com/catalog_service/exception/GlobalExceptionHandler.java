package com.catalog_service.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleNotFound(RuntimeException ex) {

        ErrorMessage error = new ErrorMessage(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(), // 404
                "Recurso não encontrado",
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationError(MethodArgumentNotValidException ex) {

        ErrorMessage error = new ErrorMessage(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), // 400
                "Erro de Validação",
                "Você esqueceu de preencher campos obrigatórios ou enviou dados inválidos."
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}