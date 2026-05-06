package com.wellington.order_service.dto;

public record OrderItemRequestDTO(
        String productId,
        Integer quantidade
) {}
