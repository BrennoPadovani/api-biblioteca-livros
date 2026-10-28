# Atividade Prática – API REST de Biblioteca Virtual (Spring Boot)

API para cadastrar, listar, buscar, atualizar e remover livros, usando Spring Data JPA e banco H2 em memória.

## Tecnologias
- Java 21+
- Spring Boot 4.1.1 (Spring Web MVC, Spring Data JPA)
- H2 Database (console em `/h2-ui`)
- Maven

## Como executar
```bash
mvn spring-boot:run
```
A API sobe em `http://localhost:8080`.

## Endpoints
| Método | URL | Descrição |
|--------|-----|-----------|
| POST | /api/livros | Cadastra um livro |
| GET | /api/livros | Lista todos os livros (aceita `?titulo=texto`) |
| GET | /api/livros/{id} | Busca um livro pelo ID |
| GET | /api/livros/autor/{autor} | Lista os livros de um autor |
| PUT | /api/livros/{id} | Atualiza um livro |
| DELETE | /api/livros/{id} | Remove um livro |
| DELETE | /api/livros | Remove todos os livros |

## Console do banco H2
`http://localhost:8080/h2-ui` — JDBC URL: `jdbc:h2:mem:testdb`, usuário `sa`, senha vazia.

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
