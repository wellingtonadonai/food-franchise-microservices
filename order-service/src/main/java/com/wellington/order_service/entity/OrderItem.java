package com.wellington.order_service.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItem {
    private String productId;
    private String nome;
    private BigDecimal precoUnitario;
    private Integer quantidade;
}
