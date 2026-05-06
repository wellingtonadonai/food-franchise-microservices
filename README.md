# 🍦 Franchise Microservices

Sistema de microsserviços para gerenciamento de catálogo e vendas de uma franquia de alimentos (Açaí / Sorveteria), utilizando arquitetura distribuída, mensageria assíncrona e banco NoSQL.

---

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot 3
- Spring Cloud Gateway
- Apache Kafka
- MongoDB
- Docker & Docker Compose
- Maven
- Lombok

---

# 🏗️ Arquitetura do Sistema

O sistema é composto por 3 microsserviços principais:

| Serviço | Porta | Responsabilidade |
|---|---|---|
| API Gateway | 8000 | Centraliza e roteia as requisições |
| Catalog Service | 8080 | Gerencia o catálogo de produtos |
| Order Service | 8081 | Processa pedidos e vendas |

---

## 🔄 Comunicação entre Serviços

O projeto utiliza comunicação assíncrona com Apache Kafka.

### Fluxo:

1. O `Catalog Service` cria/atualiza um produto
2. Uma mensagem é publicada no Kafka
3. O `Order Service` consome essa mensagem
4. O produto é salvo localmente como cache
5. Os pedidos podem ser processados sem consultar o catálogo diretamente

---

## 💰 Precisão Financeira

Os cálculos financeiros utilizam:

```java
BigDecimal
```

Garantindo precisão monetária e evitando problemas comuns com `double` e `float`.

---

# 🐳 Como Rodar o Projeto

## 1️⃣ Pré-requisitos

- Java 21
- Maven
- Docker Desktop

---

## 2️⃣ Subindo Infraestrutura

Na raiz do projeto:

```bash
docker-compose up -d
```

Isso irá subir:

- MongoDB
- Apache Kafka
- Zookeeper

---

## 3️⃣ Executando os Microsserviços

Execute na seguinte ordem:

```text
CatalogServiceApplication
OrderServiceApplication
ApiGatewayApplication
```

---

# 🔗 Endpoints Principais

## 🍦 Produtos

### Listar produtos
```http
GET /products
```

### Buscar produto por ID
```http
GET /products/{id}
```

### Criar produto
```http
POST /products
```

Exemplo:

```json
{
  "nome": "Açaí 500ml",
  "preco": 25.90
}
```

---

## 📝 Pedidos

### Realizar pedido
```http
POST /orders
```

Body:

```json
{
  "clienteNome": "Wellington",
  "itens": [
    {
      "productId": "ID_DO_PRODUTO",
      "quantidade": 2
    }
  ]
}
```

---

# 📦 Estrutura do Projeto

```text
franchise-microservices
│
├── api-gateway
├── catalog-service
├── order-service
└── docker-compose.yml
```

---

# 👨‍💻 Desenvolvedor

### Wellington Silva
Back-end Java Developer
