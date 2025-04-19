# Projeto ToDoList

Este é um projeto simples de uma API REST para gerenciar tarefas do dia-a-dia, desenvolvido em Java com Spring Boot e utilizando um banco de dados PostgreSQL.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.4**
- **Maven**
- **PostgreSQL**
- **Hibernate (JPA)**
- **JUnit**

## Funcionalidades

- Criar, atualizar, listar e excluir tarefas.
- As tarefas têm atributos como nome, descrição, status (CRIADO, FINALIZADO), observações, data de criação e data de atualização.
- Testes unitários

## Pré-requisitos

Será necessário ter as seguintes ferramentas em sua máquina:

- [JDK 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [PostgreSQL](https://www.postgresql.org/download/)
- Um cliente para testar as rotas da API, como o [Postman](https://www.postman.com/downloads/) por exemplo.
  
## Configuração do Banco de Dados

1. Crie um banco de dados no PostgreSQL:
   ```sql
   CREATE DATABASE todo_list;

2. Crie um schema no banco de dados **todo_list**:
   ```sql
   CREATE SCHEMA todo;

3. Crie a tabela **tarefa** :
   ```sql
   CREATE TABLE todo.tarefa (
    id SERIAL PRIMARY KEY,
    nome VARCHAR,
    descricao VARCHAR,
    status VARCHAR,
    observacoes VARCHAR,
    data_criacao DATE DEFAULT CURRENT_DATE,
    data_atualizacao DATE DEFAULT CURRENT_DATE);

# Executando a Aplicação

## Clone o repositório:

```
git clone https://github.com/marlon-vinicius/Projeto_ToDo_List.git
```

Abra a aplicação na sua IDE.

Carregue as depedências do Maven.

Localize o arquivo **applications.properties**.

Altere o arquivo com as propriedas do seu banco de dados desta maneira:

```
spring.application.name=todolist
server.port = 8080

spring.datasource.url=jdbc:postgresql://localhost:5432/todo_list
spring.datasource.username=postgres
spring.datasource.password=admin
spring.datasource.driver-class-name=org.postgresql.Driver
```

Execute o projeto.

# Rotas da API

# Criar uma nova tarefa
- Método: POST
- Endpoint: /api/tarefas

```
{
  "nome": "Estudar",
  "descricao": "Estudar para a prova",
  "status": "CRIADO",
  "observacoes": "Estudar até quarta-feira"
}
```

# Listar todas as tarefas
- Método: GET
- Endpoint: /api/tarefas

```
[
    {
        "id": 1,
        "nome": "Estudar",
        "descricao": "Estudar para a prova",
        "status": "CRIADO",
        "observacoes": "Estudar até quarta-feira",
        "data_criacao": "2025-04-18",
        "data_atualizacao": "2025-04-18"
    }
]
```

# Buscar uma tarefa por id
- Método: GET
- Endpoint: /api/tarefas/{id}

# Atualizar uma tarefa
- Método: PUT
- Endpoint: /api/tarefas/{id}
