package com.catalog_service.dto;

import java.math.BigDecimal;

public record ProductEventDTO(
        String id,
        String nome,
        BigDecimal preco,
        String acao // "CREATE" ou "UPDATE"
) {}
