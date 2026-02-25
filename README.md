# AutoFlex – Sistema de Gerenciamento de Estoque e Produção

Uma aplicação **full-stack** desenvolvida para gerenciar estoques de matérias-primas e fornecer **sugestões inteligentes de produção** com base em um **algoritmo guloso (Greedy Algorithm)**.  
O objetivo do projeto é **maximizar o valor da produção**, priorizando produtos com maior preço unitário.

---

## 🚀 Stack Tecnológica

- **Backend:** Java 21, Quarkus (Framework), Hibernate Panache (ORM), PostgreSQL  
- **Frontend:** React.js, Fetch API  
- **Infraestrutura:** Docker e Docker Compose  

---

## 🛠️ Pré-requisitos

Antes de começar, certifique-se de ter os seguintes itens instalados:

- Docker & Docker Compose  
- Java 21  
- Node.js (v18 ou superior)  
- Maven (ou utilize o wrapper ./mvnw)

---

## 🏗️ Como Executar o Projeto

### 1. Configuração do Banco de Dados (Docker)

```bash
docker compose up -d
```

> O banco de dados roda na porta **5433** para evitar conflitos com o PostgreSQL local.

---

### 2. Configuração do Backend (Quarkus)

```bash
./mvnw clean quarkus:dev
```

- API: http://localhost:8080  
- Swagger UI: http://localhost:8080/q/swagger-ui/

---

### 3. Configuração do Frontend (React)

```bash
npm install
npm start
```

- Frontend: http://localhost:3000

---

## 🧪 Testes

```bash
./mvnw test
```

Os testes validam a lógica do serviço de produção, garantindo cálculos corretos e controle adequado de estoque.

---

## 📡 Endpoints da API

| Método | Endpoint | Descrição |
|------|---------|----------|
| GET | /api/raw-materials | Lista todas as matérias-primas |
| POST | /api/raw-materials | Cadastra uma nova matéria-prima |
| GET | /api/products | Lista todos os produtos |
| POST | /api/products | Cadastra um produto com sua composição |
| GET | /api/production-plan | Retorna o plano de produção |

---

## 🧠 Algoritmo Guloso para testes

1. Ordena os produtos pelo maior valor unitário  
2. Calcula a produção máxima possível com o estoque disponível  
3. Desconta os materiais de um estoque virtual  
4. Repete o processo até finalizar a lista de produtos  



