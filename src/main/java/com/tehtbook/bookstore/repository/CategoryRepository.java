// src/main/java/com/tehtbook/bookstore/repository/CategoryRepository.java

package com.tehtbook.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.tehtbook.bookstore.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}

