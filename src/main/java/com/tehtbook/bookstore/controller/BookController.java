package com.tehtbook.bookstore.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
