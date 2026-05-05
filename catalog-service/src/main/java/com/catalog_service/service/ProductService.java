package com.catalog_service.service;

import com.catalog_service.ProductProducer;
import com.catalog_service.domain.Product;
import com.catalog_service.repository.ProductRepository;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ProductService {

    private final ProductRepository repository;
    private final GeminiService geminiService;
    private final ProductProducer kafka;

    public Product createProduct(Product product) {

        String textoGerado = geminiService.gerarDescricao(product.getNome(), product.getListaIngredientes());
        product.setDescricaoIA(textoGerado);
        Product salvo = repository.save(product);

        kafka.enviarEvento(salvo, "CRIADO");
        return salvo;
    }

    public List<Product> getAllProduct(){
        return repository.findAll();
    }

    public List<Product> buscarProdutoPorNome(String termoDeBusca) {
        return repository.findByNomeContainingIgnoreCase(termoDeBusca);
    }

    public void deleteProduct(String id){
        if (!repository.existsById(id)){
            throw new RuntimeException("Produto não encontrado para exclusão");
        }
        repository.deleteById(id);
    }

    public Product updateProduct(String id, Product novosDados) {

        Product produtoExistente = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado para atualização"));

        produtoExistente.setNome(novosDados.getNome());
        produtoExistente.setPreco(novosDados.getPreco());

        if (!produtoExistente.getListaIngredientes().equals(novosDados.getListaIngredientes())) {

            produtoExistente.setListaIngredientes(novosDados.getListaIngredientes());

            String novaDescricao = geminiService.gerarDescricao(
                    produtoExistente.getNome(),
                    produtoExistente.getListaIngredientes()
            );
            produtoExistente.setDescricaoIA(novaDescricao);
            System.out.println("Ingredientes alterados! Nova descrição gerada pela IA.");
        }
        Product atualizado = repository.save(produtoExistente);

        kafka.enviarEvento(atualizado, "ATUALIZADO");
        return atualizado;
    }



    public ProductService (ProductRepository repository, GeminiService geminiService, ProductProducer kafka){
        this.repository = repository;
        this.geminiService = geminiService;
        this.kafka = kafka;
    }


}



