package com.catalog_service;

import com.catalog_service.domain.Product;
import com.catalog_service.dto.ProductEventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String TEMA = "product-events";

    public void enviarEvento(Product product, String acao) {
        ProductEventDTO evento = new ProductEventDTO(
                product.getId(),
                product.getNome(),
                product.getPreco(),
                acao
        );
        kafkaTemplate.send(TEMA, evento);
    }
}
