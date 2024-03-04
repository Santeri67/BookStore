package com.tehtbook.bookstore;

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

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(BookRepository bookRepository, CategoryRepository categoryRepository) {
        return args -> {
            // Ensin luodaan kategoriat ja tallennetaan ne tietokantaan
            Category scifi = new Category("Sci-Fi");
            Category fantasy = new Category("Fantasy");
            categoryRepository.save(scifi);
            categoryRepository.save(fantasy);

            // Sitten luodaan kirjat ja liitetään ne luotuihin kategorioihin
            Book book1 = new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", 1997, "9780747532699", 10.99, scifi);
            Book book2 = new Book("A Dance with Dragons", "George R. R. Martin", 2011, "9780553801477", 15.99, fantasy);

            // Tallennetaan kirjat tietokantaan
            bookRepository.save(book1);
            bookRepository.save(book2);
        };
    }
}
