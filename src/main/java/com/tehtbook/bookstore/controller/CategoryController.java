// src/main/java/com/tehtbook/bookstore/controller/CategoryController.java
package com.tehtbook.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tehtbook.bookstore.model.Category;
import com.tehtbook.bookstore.repository.CategoryRepository;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // Olemassa olevat metodit ...

    @GetMapping("/categorylist")
    public String categoryList(Model model) {
        // Muunna Iterable<Category> List<Category>:ksi
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);
        
        model.addAttribute("categories", categories);
        return "categorylist";
    }

    @GetMapping("/addcategory")
    public String addCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory";
    }

    @PostMapping("/addcategory")
    public String addCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:/categorylist";
    }

    // Muut metodit...
}

