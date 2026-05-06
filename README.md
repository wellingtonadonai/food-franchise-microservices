Franchise Microservices - Sistema de Gestão de Vendas

Este projeto é um ecossistema de microsserviços desenvolvido para gerenciar o catálogo e as vendas de uma franquia (ex: Açaí/Sorveteria). O sistema utiliza comunicação assíncrona, banco de dados NoSQL e uma porta de entrada unificada.

🏗️ Arquitetura do Sistema

O sistema é composto por três serviços principais que trabalham em conjunto:

API Gateway (Porta 8000): O maestro do sistema. Centraliza todas as requisições e as direciona para os serviços corretos, evitando que o cliente precise lidar com múltiplas portas.

Catalog Service (Porta 8080): Gerencia o inventário de produtos. Permite criar, editar e listar itens (como açaís, sorvetes e acompanhamentos).

Order Service (Porta 8081): O motor de vendas. Processa os pedidos, calcula o valor total utilizando BigDecimal (garantindo precisão financeira) e mantém um histórico de vendas.

🛠️ Tecnologias Utilizadas
Java 21 & Spring Boot 3.x

Spring Cloud Gateway: Para o roteamento das APIs.

Apache Kafka: Para sincronização de dados entre serviços (Mensageria).

MongoDB: Banco de dados NoSQL para alta performance e flexibilidade.

Docker & Docker Compose: Para subir a infraestrutura de banco e mensageria de forma rápida.

Lombok: Para redução de código boilerplate.

🚦 Como Rodar o Projeto
1. Pré-requisitos

Docker instalado.

Java 21 instalado.

Maven.

2. Subindo a Infraestrutura (Banco e Kafka)
   Na pasta raiz, onde está o seu arquivo docker-compose.yml, execute:

Bash
docker-compose up -d

3. Rodando os Serviços


Abra cada projeto no seu IntelliJ e execute as classes principais na seguinte ordem sugerida:

CatalogServiceApplication

OrderServiceApplication

ApiGatewayApplication

🔗 Endpoints Principais (Via Gateway - Porta 8000)
🍦 Catálogo de Produtos
Listar Produtos: GET /products

Buscar por ID: GET /products/{id}

Criar Produto: POST /products (Envia JSON com nome e preço)

📝 Pedidos de Venda

Realizar Pedido: POST /orders

JSON
{
"clienteNome": "Nome do Cliente",
"itens": [
{ "productId": "ID_DO_PRODUTO", "quantidade": 2 }
]
}

Fluxo de Dados 

Quando um produto é criado no Catalog Service, ele envia uma mensagem para o Kafka.

O Order Service escuta essa mensagem e salva uma cópia (cache) no seu próprio banco MongoDB.

Isso permite que o Order Service faça vendas instantâneas sem precisar consultar o Catálogo toda hora, garantindo que o sistema nunca pare!

👨‍💻 Desenvolvedor

Wellington - Back-end Java Developer