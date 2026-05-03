# 🍔 Food Franchise Catalog Service

Uma API RESTful desenvolvida em **Java 21** e **Spring Boot** para gerenciar o catálogo de produtos de uma franquia de alimentos. O grande diferencial deste microserviço é a sua integração nativa com Inteligência Artificial Generativa.

Toda vez que um novo produto é cadastrado com seus ingredientes básicos, o sistema se comunica com a API do **Google Gemini (2.5-Flash)** para gerar automaticamente uma descrição de marketing altamente atrativa, salvando o produto finalizado em um banco de dados NoSQL (**MongoDB**).

## 🚀 Funcionalidades

- **Cadastro de Produtos (POST):** Recebe dados básicos (nome, preço, ingredientes), valida as regras de negócio e aciona a IA para gerar a descrição comercial.
- **Listagem de Produtos (GET):** Retorna o catálogo completo salvo no banco de dados.
- **Validação de Dados:** Utiliza Jakarta Validation (`@NotBlank`, `@NotNull`, `@Positive`) para garantir a integridade dos dados (ex: impede preços negativos ou produtos sem nome).
- **Tratamento Global de Erros:** Respostas de erro customizadas, amigáveis e padronizadas através de um `GlobalExceptionHandler`, blindando as regras de negócio.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 21
- **Framework:** Spring Boot 3.x
- **Banco de Dados:** MongoDB (Spring Data MongoDB)
- **Integração AI:** Google Gemini API (REST Client)
- **Validação:** Spring Boot Starter Validation (Jakarta)
- **Gerenciador de Dependências:** Maven

## ⚙️ Como Executar o Projeto Localmente

### Pré-requisitos
- Java 21 instalado na máquina.
- Um cluster do MongoDB rodando localmente ou na nuvem (MongoDB Atlas).
- Uma chave de API válida do [Google AI Studio](https://aistudio.google.com/).

### Passo a Passo

1. Clone o repositório:
```bash
git clone [https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git](https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git)

2 .Configure a sua chave da API do Google Gemini nas Variáveis de Ambiente do seu sistema operacional ou IDE:

Nome da Variável: GEMINI_API_KEY

Valor: sua_chave_secreta_aqui

Execute a aplicação usando o Maven:

Bash
mvn spring-boot:run
A aplicação estará disponível na porta 8080 (ou a configurada no application.properties).

📡 Exemplos de Uso (Endpoints)
1. Criar um novo Produto
POST /api/products

Corpo da Requisição (JSON):

JSON
{
    "nome": "Açaí Turbinado",
    "preco": 25.50,
    "listaIngredientes": [
        "Açaí",
        "Leite Condensado",
        "Morango",
        "Granola"
    ]
}
Resposta de Sucesso (201 Created):

JSON
{
    "id": "69f77e9078ebb935f03832fc",
    "nome": "Açaí Turbinado",
    "preco": 25.50,
    "listaIngredientes": [
        "Açaí",
        "Leite Condensado",
        "Morango",
        "Granola"
    ],
    "descricaoIA": "Sinta o poder do Açaí Turbinado: uma jornada de sabor com açaí cremoso, a doçura do morango, a indulgência do leite condensado e a crocância perfeita da granola, impulsionando sua energia."
}
2. Exemplo de Erro de Validação (Fail Fast)
Se tentarmos enviar um produto sem nome ou com preço negativo:

Resposta de Erro (400 Bad Request):

JSON

{
    "nome": "O nome do produto não pode ser vazio!",
    "preco": "O preço deve ser maior que zero!"
}

Desenvolvido com dedicação por Wellington.

