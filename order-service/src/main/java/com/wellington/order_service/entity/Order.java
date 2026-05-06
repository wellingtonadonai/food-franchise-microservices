package com.wellington.order_service.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
@Data
public class Order {
    @Id
    private String id;
    private String clienteNome;
    private List<OrderItem> itens;
    private BigDecimal valorTotal;
    private String status;
    private LocalDateTime dataPedido = LocalDateTime.now();


}

