# 🥟 Baozi Store API

API REST para gerenciamento da Baozi Store - uma loja especializada em pãozinho chinês.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.1.5
- Spring Data JPA
- MySQL / H2 Database
- Maven
- Postman (para testes)

## Estrutura do Projeto
baozi-store-api/
├── src/main/java/com/baozi/store/
│ ├── BaoziStoreApplication.java
│ ├── model/ # Entidades JPA
│ ├── repository/ # Repositórios Spring Data JPA
│ ├── controller/ # Controladores REST
│ └── exception/ # Tratamento de exceções
└── src/main/resources/
├── application.properties
└── data.sql


## Endpoints Disponíveis

### Clientes
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/clientes` | Criar cliente |
| GET | `/clientes` | Listar todos |
| GET | `/clientes/{id}` | Buscar por ID |
| DELETE | `/clientes/{id}` | Deletar |
| PUT | `/clientes/{id}` | Atualizar |

### Produtos
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/produtos` | Criar produto |
| GET | `/produtos` | Listar todos |
| GET | `/produtos/{id}` | Buscar por ID |
| DELETE | `/produtos/{id}` | Deletar |
| PUT | `/produtos/{id}` | Atualizar |
| GET | `/produtos/estoque` | Listar em estoque |

### Pedidos
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/pedidos` | Criar pedido |
| GET | `/pedidos` | Listar todos |
| GET | `/pedidos/{id}` | Buscar por ID |
| DELETE | `/pedidos/{id}` | Deletar |
| PUT | `/pedidos/{id}` | Atualizar |
| GET | `/pedidos/cliente/{id}` | Por cliente |
| GET | `/pedidos/produto/{id}` | Por produto |

## Como Executar

1. **Clone o repositório:**
```bash
git clone https://github.com/neinunesjunior/baozi-store-api.git
cd baozi-store-api
