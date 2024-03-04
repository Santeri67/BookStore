package com.tehtbook.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tehtbook.bookstore.model.Book;
import com.tehtbook.bookstore.model.Category;
import com.tehtbook.bookstore.repository.BookRepository;
import com.tehtbook.bookstore.repository.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

    @Autowired
    private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
    CommandLineRunner runner(BookRepository repository) {
        return args -> {
            // Luo esimerkkikirjoja ja tallenna ne tietokantaan
            Book book1 = new Book("Harry Potter and the Philosopher's stone", "J.K Rowling", 2021, "1234567890", 29.90);
            Book book2 = new Book("A Dance with Dragons", "R. R. Martin", 2019, "0987654321", 39.90);
            repository.save(book1);
            repository.save(book2);
            // ... tallenna lisää kirjoja tarvittaessa ...
            Category category1 = new Category("Scifi");
            Category category2 = new Category("Comic");
            Category category3 = new Category("Fiction");
            categoryRepository.save(category1);
            categoryRepository.save(category2);
            categoryRepository.save(category3);
        };
    }
}
