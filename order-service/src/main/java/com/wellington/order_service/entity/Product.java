package com.wellington.order_service.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products_cache")
@Data
public class Product {
    @Id
    private String id;
    private String nome;
    private Double preco;
}
