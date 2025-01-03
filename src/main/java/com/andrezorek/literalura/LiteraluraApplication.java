package com.andrezorek.literalura;

import com.andrezorek.literalura.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.andrezorek.literalura.dto.APIResponseDTO;
import io.github.cdimascio.dotenv.Dotenv;
import com.andrezorek.literalura.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;
import static com.andrezorek.literalura.utils.RequestHandler.fetch;

@SpringBootApplication
@EntityScan("com/andrezorek/literalura/models")
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private BookRepository repositorio; 

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
		System.out.println("Hello from Spring land");

		var res = fetch("https://gutendex.com/books/?search=kama");

		// Desserializar o JSON para o data transfer object record
		ObjectMapper mapper = new ObjectMapper();
		APIResponseDTO response = mapper.readValue(res, APIResponseDTO.class);

		// Exibindo o resultado com DTO
		System.out.println("Contagem de Itens Recebidos::  " + response.count());

		List<Book> books = response.results().stream()
				.map(Book::new) // transforma cada BookDTO em um Book
				.toList(); // cria a lista de Books

		books.forEach(System.out::println);
		for (Book book : books) {
			repositorio.save(book);
		}


	}
}
