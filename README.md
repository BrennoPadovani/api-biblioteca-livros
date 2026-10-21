# Atividade Prática – API REST de Biblioteca Virtual (Spring Boot)

API para cadastrar, listar, buscar, atualizar e remover livros, usando Spring Data JPA e banco H2 em memória.

## Tecnologias
- Java 17+ (testado com 21)
- Spring Boot 4.1.1 (Spring Web, Spring Data JPA)
- H2 Database
- Maven

## Como executar
```bash
mvn spring-boot:run
```
A API sobe em `http://localhost:8080`.

## Endpoints
| Método | URL | Descrição |
|--------|-----|-----------|
| POST | /livros | Cadastra um livro |
| GET | /livros | Lista todos os livros |
| GET | /livros/{id} | Busca um livro pelo ID |
| PUT | /livros/{id} | Atualiza um livro |
| DELETE | /livros/{id} | Remove um livro |

## Exemplo de corpo (POST/PUT)
```json
{
  "titulo": "Dom Casmurro",
  "autor": "Machado de Assis",
  "isbn": "9788535910663",
  "anoPublicacao": 1899
}
```

## Integrantes
- Brenno Lucas Padovani Salgado
