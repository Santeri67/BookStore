package com.tehtbook.bookstore.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tehtbook.bookstore.model.Book;
import com.tehtbook.bookstore.repository.BookRepository;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    // Konstruktori-injektointi BookRepositorylle
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/booklist")
    public String bookList(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "booklist";
    }
    @GetMapping("/addbook")
        public String showAddBookForm(Model model) {
            model.addAttribute("book", new Book());
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
