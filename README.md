# Consultório Médico API 🏥💻

Este projeto é uma API RESTful desenvolvida utilizando **Java** e **Spring Boot**, com o objetivo de gerenciar um consultório médico. A API oferece funcionalidades de cadastro e gerenciamento de médicos, pacientes e recepcionistas, além de permitir a autenticação de usuários.

## Tecnologias Usadas 🛠️

- **Java 21** ☕
- **Spring Boot** (framework principal) 🍃
- **Spring Security** (autenticação e autorização) 🔐
- **Spring Data JPA** (acesso a banco de dados) 📊
- **PostgreSQL** (banco de dados relacional) 🐘
- **BCrypt** (criptografia de senhas) 🔒

## Funcionalidades ⚙️

- **Cadastro de médicos, pacientes e recepcionistas**: Cada um com permissões específicas.
- **Autenticação baseada em login e senha** para médicos, pacientes e recepcionistas.
- **Cadastro e gerenciamento de consultas, exames médicos e histórico médico.**
- **Acesso restrito**: Médicos e pacientes podem apenas visualizar suas informações; recepcionistas podem cadastrar médicos e pacientes.

## Endpoints 📑

### Autenticação

- **POST /login**  
  Realiza o login e retorna um token JWT para autenticação nas próximas requisições.

### Médicos 👨‍⚕️

- **POST /medicos**  
  Cadastra um novo médico.

- **GET /medicos/{id}**  
  Recupera as informações de um médico por ID.

- **PUT /medicos/{id}**  
  Atualiza as informações de um médico.

- **DELETE /medicos/{id}**  
  Exclui um médico.

### Pacientes 🧑‍⚕️

- **POST /pacientes**  
  Cadastra um novo paciente.

- **GET /pacientes/{id}**  
  Recupera as informações de um paciente por ID.

### Recepcionistas 👩‍💼

- **POST /recepcionistas**  
  Cadastra um novo recepcionista.

## Como Executar o Projeto 🏃‍♂️

### Pré-requisitos 🔑

- JDK 21 ou superior
- Maven ou Gradle para gerenciamento de dependências
- **PostgreSQL** instalado e configurado
- IDE (como IntelliJ IDEA ou Eclipse) para desenvolvimento (opcional)

### Passos para Rodar 🔄

1. Clone o repositório:

```bash
git clone https://github.com/rodvpx/ApiConsultorioMedico-Spring
```

2. Navegue até a pasta do projeto:

```bash
cd consultorio-medico-api
```

3. Configure o banco de dados PostgreSQL no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco_de_dados
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

4. Se estiver utilizando Maven, execute:

```bash
mvn spring-boot:run
```

5. O projeto estará disponível na URL:

```
http://localhost:8080
```

### Testando a API 🧪

Utilize ferramentas como **Postman** ou **Insomnia** para testar os endpoints da API, realizando requisições HTTP (GET, POST, PUT, DELETE) conforme os exemplos acima.

## Estrutura de Diretórios 📂

A estrutura de diretórios do projeto segue o padrão comum de uma aplicação Spring Boot:

```
src/
 ├── main/
 │    ├── java/
 │    │    ├── com/
 │    │    │    ├── consultoriomedico/
 │    │    │    │    ├── model/            # Entidades JPA
 │    │    │    │    ├── repository/       # Repositórios Spring Data JPA
 │    │    │    │    ├── service/          # Camada de serviços
 │    │    │    │    ├── controller/       # Camada de controllers
 │    │    │    │    ├── config/           # Configurações de segurança e Spring
 ├── resources/
 │    ├── application.properties            # Configurações do projeto
 │    ├── static/                           # Arquivos estáticos (se necessário)
 │    ├── templates/                        # Templates (se houver)
 └── test/                                  # Testes unitários e de integração
```

## Autenticação e Autorização 🔑

A autenticação é feita utilizando **JWT** (JSON Web Tokens). Ao fazer login com um usuário, a resposta incluirá um token JWT que deve ser enviado no cabeçalho `Authorization` das requisições subsequentes.

Exemplo de cabeçalho para autenticação:

```
Authorization: Bearer {token}
```