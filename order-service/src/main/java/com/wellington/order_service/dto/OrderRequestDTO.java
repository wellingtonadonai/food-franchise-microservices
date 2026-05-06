package com.wellington.order_service.dto;

import java.util.List;

public record OrderRequestDTO(
        String clienteNome,
        List<OrderItemRequestDTO> itens
) {}
