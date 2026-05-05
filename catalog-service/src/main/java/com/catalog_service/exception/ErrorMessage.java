package com.catalog_service.exception;

import java.time.LocalDateTime;

public record ErrorMessage(
        LocalDateTime timestamp, // Que horas o erro aconteceu
        int status,              // O código HTTP (404, 400, etc)
        String error,           // O nome do erro (ex: "Não Encontrado")
        String message          // A frase personalizada que escrevemos no Service
) {}
