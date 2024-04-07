package com.tehtbook.bookstore.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tehtbook.bookstore.domain.User;
import com.tehtbook.bookstore.repository.UserRepository;

@Configuration
public class DataInitializerConfig {

    @Bean
    CommandLineRunner initData(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            // Lisää testikäyttäjiä tietokantaan
            userRepository.save(new User("user", passwordEncoder.encode("password"), "ROLE_USER"));
            userRepository.save(new User("admin", passwordEncoder.encode("adminpassword"), "ROLE_ADMIN"));
        };
    }
}

