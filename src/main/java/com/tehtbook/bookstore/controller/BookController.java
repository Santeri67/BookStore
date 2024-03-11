package com.tehtbook.bookstore.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tehtbook.bookstore.model.Book;
import com.tehtbook.bookstore.repository.BookRepository;
import com.tehtbook.bookstore.repository.CategoryRepository;

@RestController
public class BookController {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    // Konstruktori-injektointi BookRepositorylle
    public BookController(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository; // Lisätty rivi
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> bookList() {
        List<Book> books = bookRepository.findAll();
        return ResponseEntity.ok(books); // Palauttaa HTTP 200 OK vastauksen ja kirjalistan JSON-muodossa
    }
    @GetMapping("/books/{id}")
public ResponseEntity<Book> getBookById(@PathVariable Long id) {
    return bookRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
}
    @GetMapping("/addbook")
        public String showAddBookForm(Model model) {
            model.addAttribute("book", new Book());
            model.addAttribute("categories", categoryRepository.findAll());
            return "addbook";
}
    @PostMapping("/addbook")
        public String addBook(@ModelAttribute Book book) {
            bookRepository.save(book);
            return "redirect:/booklist";
}
    @GetMapping("/delete/{id}")
        public String deleteBook(@PathVariable("id") Long bookId) {
            if (bookId != null) {
                bookRepository.deleteById(bookId);
    }
    return "redirect:/booklist";
}
    @GetMapping("/edit/{id}")
        public String showEditBookForm(@PathVariable("id") Long bookId, Model model) {
            Book book = bookRepository.findById(bookId).orElse(null);
            model.addAttribute("book", book);
            return "editbook";
}
    @PostMapping("/editbook")
        public String editBook(@ModelAttribute Book book) {
    // Oletetaan, että kirjalla on jo 'id' asetettu, joten tämä päivittää olemassa olevan kirjan
            bookRepository.save(book);
            return "redirect:/booklist";
}
}
