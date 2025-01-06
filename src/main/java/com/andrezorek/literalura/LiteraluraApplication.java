package com.andrezorek.literalura;

import com.andrezorek.literalura.main.Main;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan("com/andrezorek/literalura/models")
public class LiteraluraApplication implements CommandLineRunner {

	private final Main principal;

	@Autowired
	public LiteraluraApplication(Main principal) {
		this.principal = principal;
	}

	public static void main(String[] args) {
		// Carrega as variáveis do .env e seta como propriedades do sistema
		Dotenv dotenv = Dotenv.load();
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// hello world
		System.out.println("Hello from Spring-land");

//		Optional<BookDTO> bookData = APIService.searchBookByName("dante");
//
//		// Cria o livro
//		Book createdBook = bookService.createBook(bookData);
//
//		// Exibe o livro criado
//		System.out.println(createdBook);

		principal.execute();



	}
}
