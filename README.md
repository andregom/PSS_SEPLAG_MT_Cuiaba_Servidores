# 🏛️ Servidores API

API REST para gerenciamento de servidores efetivos e temporários, unidades e lotações, com autenticação via JWT, upload de imagens no MinIO e banco de dados PostgreSQL.

---

## 📦 Stack Utilizada

- **Java 21**
- **Spring Boot 3**
- **PostgreSQL 15** (via Docker)
- **MinIO** (S3-compatible, via Docker)
- **Docker Compose**
- **JWT Token com Refresh Token**
- **Swagger UI**
- **Flyway (migrações do banco de dados)**
- **Java Faker** (seed de dados fake para testes)

---

## 🚀 Como Rodar o Projeto (Modo Dev)

### ✅ Pré-requisitos

- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [Java 21](https://adoptium.net/) instalado (apenas se for rodar localmente sem docker)
- (Opcional) `make` instalado no Windows via [GnuWin32](http://gnuwin32.sourceforge.net/packages/make.htm) ou WSL

---

### 📁 Estrutura de Pastas

```text
├── src/
│   ├── main/
│   │   └── java/br/gov/mt/seplag/servidores
│   └── resources/
│       └── application.yml
├── Dockerfile
├── docker-compose.yml
├── .env
├── Makefile
└── README.md
```

### ✅ Modo Simples (com Docker)

```bash
  docker compose --env-file .env.dev.dev -f docker-compose.yml --profile dev up --build
```

### ✅ Modo Produção
```bash
  docker compose --env-file .env.dev.prod -f docker-compose.yml --profile prod up --build
```

### 🧼 Comandos Úteis
#### Derrubar containers e apagar volumes
```bash
  docker compose down -v
```
#### Rebuild sem cache
```bash
  docker compose down -v
```
#### Derrubar containers e apagar volumes
```bash
  docker compose down -v
```
