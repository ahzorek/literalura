package com.andrezorek.literalura.main;

import com.andrezorek.literalura.dto.BookDTO;
import com.andrezorek.literalura.models.Author;
import com.andrezorek.literalura.models.Book;
import com.andrezorek.literalura.models.Language;
import com.andrezorek.literalura.services.APIService;
import com.andrezorek.literalura.services.AuthorService;
import com.andrezorek.literalura.services.BookService;
import com.andrezorek.literalura.utils.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Main {

    Scanner scanner = new Scanner(System.in);
    private final BookService bookService;
    private final AuthorService authorService;

    // Injeção de dependência
    @Autowired
    public Main(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }


    public void execute(){
        List<String> options = new ArrayList<>();
        options.add("Sair");
        options.add("Busca de livro por título");
        options.add("Listagem de todos os livros");
        options.add("Lista de autores");
        options.add("Listar autores vivos em determinado ano");
        options.add("Listar livros por Idioma");

        // Instanciando o menu principal
        Menu menu = new Menu(options);
        menu.setMenuMessage("Bem-vindo à Biblioteca Literalura! Escolha uma opção:");

        int op;

        do {
            // Exibe o menu
            menu.display();
            System.out.print("Digite sua escolha: ");

            // Captura e valida a entrada
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, digite um número válido.");
                scanner.next(); // Descartar entrada inválida
            }
            op = scanner.nextInt();

            // Processa a escolha
            switch (op) {
                case 1 -> this.searchBook();
                case 2 -> this.listAllBooks();
                case 3 -> this.listAllAuthors();
                case 4 -> this.listAuthorsAlive();
                case 5 -> this.listBooksByLanguage();
                case 0 -> System.out.println("Saindo da aplicação...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }

            System.out.println(); // Linha em branco para espaçamento

        } while (op != 0);

        scanner.close();
        System.out.println("Obrigado por usar a Biblioteca Literalura!");
    }

    private void searchBook(){
        String query;
        System.out.println("Digite o nome do livro que deseja adicionar ao banco: ");
        scanner.nextLine(); //limpa o buffer
        query = scanner.nextLine(); // recebe input do usuario

        try {
            Optional<BookDTO> bookData = APIService.searchBookByName(query.replace(" ", "+"));

            // se o livro foi encontrado, cria o livro e passa o book
            bookData.ifPresentOrElse(
                    book -> {
                        System.out.println("Livro encontrado: " + book.title());
                        Book newBook = bookService.createBook(book); // verificado que bookDto existe e cumpre os requisitos passa para a criação de um novo Book
                        System.out.println(newBook.getTitle() + " criado com sucesso.");
                    },
                    () -> System.out.println("Nenhum livro encontrado com o nome: " + query) // não encontrado, mostra a mensagem de erro
            );
        } catch (Exception e) {

            System.out.println("Erro ao buscar o livro: " + e.getMessage());
        }
    }


    private void listAllBooks(){
        System.out.println("Exibindo todos os livros :: ");
        List<Book> books = bookService.findAllBooks();
        if (books.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private void listAuthorsAlive(){
        int year;
        System.out.println("Digite em que ano você gostaria de buscar autores: ");
        scanner.nextLine(); // limpar buffer
        year = scanner.nextInt();

        List<Author> authors = authorService.getAuthorsAliveInYear(year);
        authors.forEach(System.out::println);
    }

    private void listAllAuthors(){
        List<Author> authors = authorService.getAll();
        authors.forEach(System.out::println);
    }

    private void listBooksByLanguage(){
        // Criar uma lista de opções de idiomas
        List<String> languageOptions = new ArrayList<>();
        for (Language language : Language.values()) {
            languageOptions.add(language.name()); // Usa o nome do enum como opção
        }
        languageOptions.add(0, "Voltar"); // Adiciona a opção de sair como a primeira

        // Instanciar o Menu com as opções de idiomas
        Menu languageMenu = new Menu(languageOptions);
        languageMenu.setMenuMessage("Escolha um idioma:");

        // Exibir o menu e capturar a escolha do usuário
        languageMenu.display();
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        // Processar a escolha
        if (choice == 0) {
            System.out.println("Voltando.");
            return;
        }

        if (choice > 0 && choice < languageOptions.size()) {
            String selectedLanguageName = languageOptions.get(choice);
            Language selectedLanguage = Language.valueOf(selectedLanguageName);

            System.out.println("Livros no idioma: " + selectedLanguage.name());
            bookService.getBooksByLanguage(selectedLanguage).forEach(book ->
                    System.out.println(book.getTitle())
            );

            long count = bookService.countBooksByLanguage(selectedLanguage);
            System.out.println("Total de livros em " + selectedLanguage.name() + ": " + count);
        } else {
            System.out.println("Opção inválida!");
        }
    }
}
