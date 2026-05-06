package com.wellington.order_service.dto;

public record ProductEventDTO(
        String id,
        String nome,
        Double preco,
        String acao
) {
}
