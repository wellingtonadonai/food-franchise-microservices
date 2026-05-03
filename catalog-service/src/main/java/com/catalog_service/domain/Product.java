package com.catalog_service.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {


    @Id
    private String id;
    private String nome;
    private BigDecimal preco;
    private List<String> listaIngredientes;
    private String descricaoIA;


}
