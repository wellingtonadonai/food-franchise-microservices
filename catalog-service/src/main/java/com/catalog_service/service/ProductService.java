package com.catalog_service.service;

import com.catalog_service.domain.Product;
import com.catalog_service.dto.GeminiRequest;
import com.catalog_service.dto.GeminiResponse;
import com.catalog_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;


@Service
public class ProductService {

    private final ProductRepository repository;
    private final RestClient restClient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;


    public Product createProduct(Product product) {

        String prompt = "Atue como um especialista de marketing em culinária. Crie uma única descrição curta, apetitosa e chamativa (máximo 2 frases) para um produto chamado " + product.getNome() + " que contém os seguintes ingredientes: " + product.getListaIngredientes() +
        ". REGRA IMPORTANTE: Retorne APENAS o texto da descrição, sem nenhuma frase de introdução, sem opções e sem numerações.";
        GeminiRequest requestBody = new GeminiRequest(List.of(
                new GeminiRequest.Content(List.of(
                        new GeminiRequest.Content.Part(prompt)
                ))
        ));

        try {

            GeminiResponse response = restClient.post()
                    .uri(geminiApiUrl + "?key=" + geminiApiKey)
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                    .retrieve()
                    .body(GeminiResponse.class);
            String iaText = response.candidates().get(0).content().parts().get(0).text();

            product.setDescricaoIA(iaText);

        } catch (Exception e) {
            System.err.println("Erro ao comunicar com a Inteligência Artificial: " + e.getMessage());
            product.setDescricaoIA("Descrição indisponível no momento.");
        }

        return repository.save(product);
    }

    public List<Product> getAllProduct(){
        return repository.findAll();
    }
    public ProductService (ProductRepository repository,RestClient.Builder builder){
        this.repository = repository;
        this.restClient = builder.build();
    }
    }



