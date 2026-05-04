package com.catalog_service.service;

import com.catalog_service.dto.GeminiRequest;
import com.catalog_service.dto.GeminiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class GeminiService {

    private final RestClient restClient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public GeminiService(RestClient.Builder builder){
        this.restClient = builder.build();
    }

    public String gerarDescricao(String nomeProduto, List<String> ingredientes) {

        String prompt = "Atue como um especialista de marketing em culinária. Crie uma única descrição curta, apetitosa e chamativa (máximo 2 frases) para um produto chamado " + nomeProduto + " que contém os seguintes ingredientes: " + ingredientes +
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

            // Retorna o texto que a IA gerou
            return response.candidates().get(0).content().parts().get(0).text();

        } catch (Exception e) {
            System.err.println("Erro ao comunicar com a Inteligência Artificial: " + e.getMessage());
            // Se der erro, retorna essa frase padrão
            return "Descrição indisponível no momento.";
        }
    }
}