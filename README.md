# url-encoder-backend

[![Build Status](https://travis-ci.com/gitlucaslima/url-encoder-backend.svg?branch=main)](https://travis-ci.com/gitlucaslima/url-encoder-backend)
[![License](https://img.shields.io/github/license/SEU_USUARIO/url-encoder-backend)](LICENSE)

Uma API reativa para encurtamento e redirecionamento de URLs. Desenvolvida com **Quarkus** e **MongoDB**, essa aplicação oferece uma maneira eficiente de criar links curtos e gerenciar suas informações via API RESTful.

## Funcionalidades

- **Criar URL curta**: Gera um código curto para uma URL longa fornecida.
- **Listar URLs encurtadas**: Recupera todas as URLs encurtadas armazenadas.
- **Obter informações de uma URL curta**: Exibe informações detalhadas sobre uma URL encurtada.
- **Redirecionamento**: Redireciona a URL curta para a URL original associada.

## Tecnologias Utilizadas

- **Quarkus**: Framework Java reativo para APIs de alta performance.
- **MongoDB**: Banco de dados NoSQL para armazenamento persistente de URLs.
- **Mutiny**: Biblioteca reativa para programação assíncrona.
- **RESTful API**: Comunicação com o backend via endpoints HTTP.

## Como Rodar o Projeto

### Pré-requisitos

- Java 11 ou superior
- Docker (para MongoDB, se necessário)
- Maven (para compilar o projeto)

### Passos para execução

1. Clone o repositório:

   ```bash
   git clone https://github.com/SEU_USUARIO/url-encoder-backend.git
   cd url-encoder-backend
   ```

2. Compile o projeto usando Maven:

   ```bash
   ./mvnw clean install
   ```

3. Para rodar a aplicação localmente, utilize o comando:

   ```bash
   ./mvnw quarkus:dev
   ```

4. A API estará disponível em: [http://localhost:8080](http://localhost:8080)

## Endpoints

### `POST /shorten`

Cria uma URL curta.

**Request**:

```json
{
  "originalUrl": "http://exemplo.com"
}
```

**Response**:

```json
{
  "originalUrl": "http://exemplo.com",
  "shortCode": "abc123",
  "shortUrl": "http://localhost:8080/abc123"
}
```

### `GET /shorten/all`

Lista todas as URLs encurtadas.

**Response**:

```json
[
  {
    "originalUrl": "http://exemplo.com",
    "shortCode": "abc123",
    "shortUrl": "http://localhost:8080/abc123"
  },
  ...
]
```

### `GET /shorten/info/{shortCode}`

Recupera informações sobre uma URL curta.

**Path Parameter**: `shortCode` (Exemplo: `abc123`)

**Response**:

- Se encontrada: `200 OK` com os detalhes da URL original e código curto.
- Se não encontrada: `404 Not Found`.

### `GET /shorten/{shortCode}`

Redireciona para a URL original com base no código curto.

**Path Parameter**: `shortCode` (Exemplo: `abc123`)

**Response**: Realiza um redirecionamento para a URL original associada ao código curto.

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para enviar **pull requests** ou **issues**.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

---