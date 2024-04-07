package com.tehtbook.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.tehtbook.bookstore.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
    // Tässä voit määritellä custom-hakuja, esimerkiksi käyttäjänimen perusteella
    User findByUsername(String username);
}
