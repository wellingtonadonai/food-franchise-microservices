package com.catalog_service.controller;


import com.catalog_service.domain.Product;
import com.catalog_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
        Product saveProduct = productService.createProduct(product);
        return ResponseEntity.status(201).body(saveProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> buscarProduto(){
        List<Product> catalog = productService.getAllProduct();
        return ResponseEntity.ok(catalog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> atualizarProduto(@PathVariable String id, @Valid @RequestBody Product product  ) {
        Product produtoAtualizado = productService.updateProduct(id, product);
        return ResponseEntity.ok(produtoAtualizado);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProdutoId(@PathVariable String id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
