
---

# URL Encoder Backend

[![Build Status](https://travis-ci.com/gitlucaslima/url-encoder-backend.svg?branch=main)](https://travis-ci.com/gitlucaslima/url-encoder-backend)  
[![License](https://img.shields.io/github/license/SEU_USUARIO/url-encoder-backend)](LICENSE)

Uma API reativa para encurtamento e redirecionamento de URLs. Desenvolvida com **Quarkus** e **MongoDB**, a aplicação é projetada para oferecer uma maneira eficiente de criar e gerenciar links curtos por meio de uma API RESTful.

## 🚀 Funcionalidades

- **Criação de URLs curtas**: Converte URLs longas em versões curtas e gerenciáveis.
- **Listagem de URLs**: Recupera todas as URLs encurtadas armazenadas.
- **Informações detalhadas**: Obtém detalhes sobre URLs curtas específicas.
- **Redirecionamento rápido**: Redireciona URLs curtas para seus destinos originais.

---

## 🛠️ Tecnologias Utilizadas

- **[Quarkus](https://quarkus.io/)**: Framework Java moderno e reativo para alta performance.
- **[MongoDB](https://www.mongodb.com/)**: Banco de dados NoSQL para armazenamento de URLs.
- **[Mutiny](https://smallrye.io/smallrye-mutiny/)**: Biblioteca reativa para programação assíncrona.
- **RESTful API**: Comunicação backend baseada em HTTP.

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

- **Java** 11 ou superior.
- **Docker** (para o MongoDB).
- **Maven** ou **Gradle** (para build e execução).

### Passos para Execução

1. **Clone o Repositório**:

   ```bash
   git clone https://github.com/SEU_USUARIO/url-encoder-backend.git
   cd url-encoder-backend
   ```

2. **Compile o Projeto**:

   Usando Maven:
   ```bash
   ./mvnw clean install
   ```
   Usando Gradle:
   ```bash
   ./gradlew build
   ```

3. **Inicie a Aplicação**:

   Com Maven:
   ```bash
   ./mvnw quarkus:dev
   ```
   Com Gradle:
   ```bash
   ./gradlew quarkusDev
   ```

4. **Endpoint Local**:  
   Acesse [http://localhost:8080](http://localhost:8080).

5. **Criar a Imagem Docker** (opcional):

   ```bash
   docker build -f src/main/docker/Dockerfile.jvm -t url-encoder-backend .
   ```

---

## 📚 Endpoints da API

### **1. POST /shorten**

**Descrição**: Cria uma URL curta.

**Exemplo de Request**:

```json
{
  "originalUrl": "https://exemplo.com"
}
```

**Exemplo de Response**:

```json
{
  "originalUrl": "https://exemplo.com",
  "shortCode": "abc123",
  "shortUrl": "http://localhost:8080/abc123"
}
```

---

### **2. GET /shorten/all**

**Descrição**: Lista todas as URLs curtas registradas.

**Exemplo de Response**:

```json
[
  {
    "originalUrl": "https://exemplo.com",
    "shortCode": "abc123",
    "shortUrl": "http://localhost:8080/abc123"
  }
]
```

---

### **3. GET /shorten/info/{shortCode}**

**Descrição**: Obtém informações detalhadas de uma URL curta.

- **Path Parameter**: `shortCode` (Exemplo: `abc123`).

**Responses**:
- **200 OK**: Detalhes da URL original.
- **404 Not Found**: Código curto não encontrado.

---

### **4. GET /shorten/{shortCode}**

**Descrição**: Redireciona diretamente para a URL original associada ao código curto.

- **Path Parameter**: `shortCode` (Exemplo: `abc123`).

**Comportamento**:
- **302 Found**: Redireciona automaticamente o cliente.

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. Crie um fork do repositório.
2. Crie uma nova branch para suas alterações:
   ```bash
   git checkout -b minha-feature
   ```
3. Faça suas alterações e envie um pull request.

---

## 📜 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

---