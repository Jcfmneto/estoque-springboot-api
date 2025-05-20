# 📦 Sistema de Gerenciamento de Estoque

Este é um sistema completo de gerenciamento de estoque desenvolvido com **Spring Boot**, que inclui autenticação via **JWT**, persistência com **MySQL**, documentação automática com **Swagger (SpringDoc)**

---

## 🧰 Tecnologias Utilizadas

- ✅ Java 17+
- ✅ Spring Boot
- ✅ Spring Security + JWT
- ✅ Spring Data JPA
- ✅ MySQL (via Docker)
- ✅ Maven
- ✅ OpenAPI (Swagger)

---

## ⚙️ Funcionalidades

- 🔐 Cadastro e login de usuários com autenticação JWT
- 📦 Cadastro de produtos
- 🔄 Registro de movimentações de estoque (entrada e saída)
- 📜 Histórico de movimentações
- 🛑 Tratamento global de exceções
- 📑 Documentação automática da API com Swagger

---

## 🐳 Subindo o MySQL com Docker

Antes de executar o projeto, inicie um container MySQL:

```bash
docker run --name estoque-mysql \
  -e MYSQL_ROOT_PASSWORD=senha \
  -e MYSQL_DATABASE=estoque \
  -p 3306:3306 \
  -d mysql:8.0
```
💡 Certifique-se de que o application.properties está configurado com essas credenciais.

🚀 Como Executar o Projeto
```bash
Copiar
Editar
# Clone o repositório
git clone https://github.com/seuusuario/projeto-estoque.git
cd projeto-estoque/demo

# Execute o projeto
./mvnw spring-boot:run
```

A aplicação estará disponível em: http://localhost:8080
Documentação Swagger: http://localhost:8080/swagger-ui.html

🗂️ Estrutura do Projeto
```bash
Copiar
Editar
com.gerenciamento.estoque
├── controllers              # Controladores REST
├── estoque                 # Lógica de movimentação de estoque
│   ├── dto
│   ├── model
│   └── repository
├── produtos                # Gestão de produtos
│   ├── dto
│   ├── model
│   └── repository
├── user                    # Gestão de usuários e autenticação
│   ├── dto
│   ├── model
│   └── repository
├── services                # Regras de negócio reutilizáveis
├── infra                   # Segurança, configuração e exceções
└── DemoApplication         # Classe principal
```
🔒 Segurança
Endpoint de login: POST /auth/login

Endpoint de registro: POST /auth/register

JWT retornado no login deve ser enviado no header:

Authorization: Bearer <token>

---

✍️ Autor
Desenvolvido por Júlio Cesar França de Moura Neto
🔗 LinkedIn • https://www.linkedin.com/in/juliomouraneto/


