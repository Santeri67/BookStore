package com.tehtbook.bookstore.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tehtbook.bookstore.model.Book;
import com.tehtbook.bookstore.repository.BookRepository;
import com.tehtbook.bookstore.repository.CategoryRepository;

@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    // Konstruktori-injektointi BookRepositorylle
    public BookController(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> bookList() {
        List<Book> books = bookRepository.findAll();
        return ResponseEntity.ok(books);
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
            bookRepository.save(book);
            return "redirect:/booklist";
}
// Voit lisätä tämän olemassa olevaan kontrolleriin
    @GetMapping("/login")
        public String login() {
        return "login";
}
@GetMapping("/booklist")
public String bookList(Model model) {
    List<Book> books = bookRepository.findAll();
    model.addAttribute("books", books);
    return "booklist"; // Olettaen, että sinulla on booklist.html tiedosto templates-kansiossa
}

}
