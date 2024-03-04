package com.tehtbook.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tehtbook.bookstore.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}