# 🚀 FórumHub - API REST

API REST completa para gerenciamento de tópicos de fórum, desenvolvida com **Spring Boot 3** e **Java 17**.

## 📋 Funcionalidades

- ✅ **Criar** novos tópicos
- ✅ **Listar** todos os tópicos
- ✅ **Buscar** tópico específico por ID
- ✅ **Atualizar** tópicos existentes
- ✅ **Deletar** tópicos
- ✅ **Validações** de dados
- ✅ **Tratamento** de erros
- ✅ **Autenticação** configurada

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5.3**
- **Spring Data JPA**
- **Spring Security**
- **Spring Validation**
- **MySQL** / **H2 Database**
- **Flyway** (Migrações)
- **Lombok**
- **Maven**

## ⚙️ Configuração e Instalação

### 1. Pré-requisitos

- **Java JDK 17+**
- **Maven 4+**
- **MySQL 8+** (ou use H2 para desenvolvimento)

### 2. Clonar o Repositório

```bash
git clone <url-do-repositorio>
cd demo
```

### 3. Configurar Banco de Dados

#### Opção A: MySQL (Produção)
```sql
-- Criar banco de dados
CREATE DATABASE forumhub;
```

Configure as credenciais em `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/forumhub
spring.datasource.username=root
spring.datasource.password=SUA_SENHA_AQUI
```

#### Opção B: H2 Database (Desenvolvimento)
Execute com o perfil `dev` - não precisa configurar nada!

### 4. Executar a Aplicação

#### MySQL:
```bash
./mvnw spring-boot:run
```

#### H2 Database:
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### 5. Acessar a Aplicação

- **API**: http://localhost:8080
- **H2 Console** (modo dev): http://localhost:8080/h2-console

## 📚 Documentação da API

### Base URL
```
http://localhost:8080
```

### Estrutura do Tópico

```json
{
  "id": 1,
  "titulo": "Como usar Spring Boot?",
  "mensagem": "Gostaria de aprender sobre Spring Boot...",
  "dataCriacao": "2024-01-15T10:30:00",
  "status": "ABERTO",
  "autor": "João Silva",
  "curso": "Java Spring"
}
```

### Status Disponíveis
- `ABERTO` - Tópico em discussão
- `FECHADO` - Tópico encerrado
- `SOLUCIONADO` - Tópico resolvido

## 🔗 Endpoints da API

### 1. 📋 Listar Todos os Tópicos

```http
GET /topicos
```

**Resposta:**
```json
[
  {
    "id": 1,
    "titulo": "Como usar Spring Boot?",
    "mensagem": "Gostaria de aprender sobre Spring Boot...",
    "dataCriacao": "2024-01-15T10:30:00",
    "status": "ABERTO",
    "autor": "João Silva",
    "curso": "Java Spring"
  }
]
```

### 2. 🔍 Buscar Tópico por ID

```http
GET /topicos/{id}
```

**Exemplo:**
```http
GET /topicos/1
```

**Resposta (200 OK):**
```json
{
  "id": 1,
  "titulo": "Como usar Spring Boot?",
  "mensagem": "Gostaria de aprender sobre Spring Boot...",
  "dataCriacao": "2024-01-15T10:30:00",
  "status": "ABERTO",
  "autor": "João Silva",
  "curso": "Java Spring"
}
```

**Resposta (404 Not Found):**
```json
{
  "erro": "Tópico não encontrado"
}
```

### 3. ➕ Criar Novo Tópico

```http
POST /topicos
Content-Type: application/json
```

**Body:**
```json
{
  "titulo": "Como usar Spring Boot?",
  "mensagem": "Gostaria de aprender sobre Spring Boot e suas principais funcionalidades.",
  "autor": "João Silva",
  "curso": "Java Spring"
}
```

**Resposta (201 Created):**
```json
{
  "id": 1,
  "titulo": "Como usar Spring Boot?",
  "mensagem": "Gostaria de aprender sobre Spring Boot e suas principais funcionalidades.",
  "dataCriacao": "2024-01-15T10:30:00",
  "status": "ABERTO",
  "autor": "João Silva",
  "curso": "Java Spring"
}
```

### 4. ✏️ Atualizar Tópico

```http
PUT /topicos/{id}
Content-Type: application/json
```

**Body (campos opcionais):**
```json
{
  "titulo": "Novo título",
  "mensagem": "Nova mensagem",
  "status": "SOLUCIONADO",
  "autor": "Novo autor",
  "curso": "Novo curso"
}
```

**Resposta (200 OK):**
```json
{
  "id": 1,
  "titulo": "Novo título",
  "mensagem": "Nova mensagem",
  "dataCriacao": "2024-01-15T10:30:00",
  "status": "SOLUCIONADO",
  "autor": "Novo autor",
  "curso": "Novo curso"
}
```

### 5. 🗑️ Deletar Tópico

```http
DELETE /topicos/{id}
```

**Resposta (204 No Content):** Sem conteúdo

## 📝 Exemplos de Uso

### cURL

#### Criar Tópico:
```bash
curl -X POST http://localhost:8080/topicos \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Dúvida sobre JPA",
    "mensagem": "Como fazer relacionamentos no JPA?",
    "autor": "Maria Santos",
    "curso": "Spring Data JPA"
  }'
```

#### Buscar Tópico:
```bash
curl http://localhost:8080/topicos/1
```

#### Atualizar Status:
```bash
curl -X PUT http://localhost:8080/topicos/1 \
  -H "Content-Type: application/json" \
  -d '{"status": "SOLUCIONADO"}'
```

### Postman/Insomnia

1. **Importe** a collection com os endpoints
2. **Configure** a base URL: `http://localhost:8080`
3. **Teste** os endpoints conforme documentação

## ⚠️ Validações

### Campos Obrigatórios (POST):
- `titulo` - Não pode estar vazio
- `mensagem` - Não pode estar vazio
- `autor` - Não pode estar vazio
- `curso` - Não pode estar vazio

### Respostas de Erro:
```json
{
  "titulo": "Título é obrigatório",
  "mensagem": "Mensagem é obrigatória"
}
```

## 🔒 Segurança

- **Spring Security** configurado
- Endpoints `/topicos/**` liberados para desenvolvimento
- Pronto para implementação de JWT

## 🗄️ Banco de Dados

### Estrutura da Tabela `topicos`:

| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | BIGINT | ID único (auto-increment) |
| titulo | VARCHAR(255) | Título do tópico |
| mensagem | TEXT | Conteúdo da mensagem |
| data_criacao | TIMESTAMP | Data/hora de criação |
| status | VARCHAR(50) | Status do tópico |
| autor | VARCHAR(100) | Nome do autor |
| curso | VARCHAR(100) | Nome do curso |

### Migrações Flyway:
- `V1__create_table_topicos.sql` - Criação da tabela

## 🚨 Troubleshooting

### Erro de Conexão MySQL:
```
Access denied for user 'root'@'localhost'
```

**Solução:**
1. Verifique as credenciais em `application.properties`
2. Ou use H2: `./mvnw spring-boot:run -Dspring-boot.run.profiles=dev`

### Porta 8080 em Uso:
```properties
server.port=8081
```

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch: `git checkout -b feature/nova-funcionalidade`
3. Commit: `git commit -m 'Adiciona nova funcionalidade'`
4. Push: `git push origin feature/nova-funcionalidade`
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT.

---

**Desenvolvido com ❤️ para o desafio FórumHub** 