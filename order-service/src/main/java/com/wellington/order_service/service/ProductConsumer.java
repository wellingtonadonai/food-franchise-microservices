package com.wellington.order_service.service;

import com.wellington.order_service.dto.ProductEventDTO;
import com.wellington.order_service.entity.Product;
import com.wellington.order_service.repository.ProductRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductConsumer {

    private final ProductRepository repository;

    @KafkaListener(topics = "product-events", groupId = "order-group")
    public void consume(ProductEventDTO event) {
        System.out.println("Recebi atualização do produto: " + event.nome());

        Product product = new Product();
        product.setId(event.id());
        product.setNome(event.nome());
        product.setPreco(BigDecimal.valueOf(event.preco()));

        repository.save(product);
        System.out.println("Produto sincronizado com sucesso!");
    }

    public ProductConsumer(ProductRepository repository) {
        this.repository = repository;
    }
}
