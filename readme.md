#### Praticando Spring Boot: Challenge LiterAlura
# Literalura - Catálogo de Livros

## Descrição do Projeto

O **Literalura** é um catálogo de livros interativo, desenvolvido como um desafio de programação. O objetivo é fornecer uma plataforma de consulta e interação com livros, buscando informações de uma API externa, processando os dados, armazenando-os em um banco de dados e oferecendo opções de interação via console para o usuário.

## Tecnologias Utilizadas

- **Java 17**: A linguagem principal utilizada para o desenvolvimento do projeto.
- **Spring Boot 3.4.1**: Framework para facilitar a criação da aplicação.
- **PostgreSQL**: Banco de dados relacional para persistência de dados.
- **API Gutendex**: Fonte externa de livros utilizada para popular o banco de dados.
- **JPA (Java Persistence API)**: Para manipulação e consulta dos dados no banco de dados.
- **Jackson**: Para processamento e conversão de JSON.

## Funcionalidades

1. **Consulta de Livros por Idioma**: O sistema permite que o usuário consulte a quantidade de livros disponíveis em um determinado idioma.
2. **Listagem de Livros**: É possível listar os livros de um determinado idioma ou autor.
3. **Filtragem de Livros por Data de Nascimento e Morte de Autor**: O usuário pode consultar livros de autores que nasceram antes ou morreram depois de determinadas datas.
4. **Interação via Console**: O sistema oferece um menu de interação com o usuário, onde ele pode escolher diferentes opções, como consultar livros, filtrar por autor, idioma, etc.

## Estrutura do Projeto

O projeto está estruturado da seguinte maneira:

./src
├── ./src/test
│   ├── ./src/test/java
│   │   ├── ./src/test/java/com
│   │   │   ├── ./src/test/java/com/andrezorek
│   │   │   │   ├── ./src/test/java/com/andrezorek/literalura
│   │   │   │   │   └── LiteraluraApplicationTests.java
├── ./src/main
│   ├── ./src/main/resources
│   │   └── ./src/main/resources/application.properties
│   ├── ./src/main/java
│   │   ├── ./src/main/java/com
│   │   │   ├── ./src/main/java/com/andrezorek
│   │   │   │   ├── ./src/main/java/com/andrezorek/literalura
│   │   │   │   │   ├── ./src/main/java/com/andrezorek/literalura/dto
│   │   │   │   │   │   ├── BookDTO.java
│   │   │   │   │   │   ├── APIResponseDTO.java
│   │   │   │   │   │   └── AuthorDTO.java
│   │   │   │   │   ├── ./src/main/java/com/andrezorek/literalura/repository
│   │   │   │   │   │   ├── AuthorRepository.java
│   │   │   │   │   │   └── BookRepository.java
│   │   │   │   │   ├── ./src/main/java/com/andrezorek/literalura/services
│   │   │   │   │   │   ├── APIService.java
│   │   │   │   │   │   ├── AuthorService.java
│   │   │   │   │   │   └── BookService.java
│   │   │   │   │   ├── ./src/main/java/com/andrezorek/literalura/models
│   │   │   │   │   │   ├── Author.java
│   │   │   │   │   │   ├── Book.java
│   │   │   │   │   │   └── Language.java
│   │   │   │   │   ├── ./src/main/java/com/andrezorek/literalura/utils
│   │   │   │   │   │   ├── RequestHandler.java
│   │   │   │   │   │   ├── Menu.java
│   │   │   │   │   └── ./src/main/java/com/andrezorek/literalura/main
│   │   │   │   │       └── Main.java
│   │   │   │   └── LiteraluraApplication.java



## Como Rodar

1. Clone o repositório:
    ```bash
    git clone https://github.com/andrezorek/literalura.git
    ```

2. Navegue até o diretório do projeto:
    ```bash
    cd literalura
    ```

3. Instale as dependências:
    ```bash
    mvn clean install
    ```

4. Certifique-se de que o banco de dados PostgreSQL esteja configurado corretamente no arquivo `application.properties`.

5. Execute a aplicação:
    ```bash
    mvn spring-boot:run
    ```

## Uso da API Gutendex

A API **Gutendex** é utilizada para obter informações sobre os livros que são populados no banco de dados. Ela fornece dados em formato JSON, que são processados e armazenados na aplicação para permitir buscas e interações do usuário.

A URL base da API é: [https://gutendex.com/](https://gutendex.com/)

## Licença

Este projeto está licenciado sob a MIT License - veja o arquivo [LICENSE](LICENSE) para mais detalhes.
