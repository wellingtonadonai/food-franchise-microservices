package com.catalog_service.service;

import com.catalog_service.domain.Product;
import com.catalog_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;


    public Product createProduct(Product product){

        product.setDescricaoIA("Descrição incrível gerada pela IA será inserida aqui em breve!");

        return repository.save(product);

    }
}
