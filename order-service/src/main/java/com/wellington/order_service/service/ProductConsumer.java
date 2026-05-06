package com.wellington.order_service.service;

import com.wellington.order_service.dto.ProductEventDTO;
import com.wellington.order_service.entity.Product;
import com.wellington.order_service.repository.ProductRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;


import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductConsumer {

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "product-events", groupId = "order-group")
    public void consume(String message) {
        try {
            log.info("#### Nova mensagem recebida do Kafka: {}", message);

            // 1. Traduz o texto (String) para o nosso DTO
            ProductEventDTO event = objectMapper.readValue(message, ProductEventDTO.class);

            // 2. Converte o DTO para a nossa Entidade de banco
            Product product = new Product();
            product.setId(event.id());
            product.setNome(event.nome());
            product.setPreco(BigDecimal.valueOf(event.preco()));

            // 3. Salva no banco 'orders_db'
            productRepository.save(product);

            log.info("#### Produto {} sincronizado com sucesso no Order Service!", event.nome());

        } catch (Exception e) {
            log.error("#### Erro crítico ao processar mensagem do Kafka: {}", e.getMessage());
            // Aqui você poderia enviar para uma fila de erros (Dead Letter Queue) no futuro
        }
    }
}