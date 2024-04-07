package com.tehtbook.bookstore.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tehtbook.bookstore.domain.User;
import com.tehtbook.bookstore.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User currentUser = userRepository.findByUsername(username);
        if (currentUser == null) {
            throw new UsernameNotFoundException("Username not found: " + username);
        }
        
        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
        builder.password(currentUser.getPasswordHash());
        builder.authorities(Collections.singletonList(new SimpleGrantedAuthority(currentUser.getRole())));
        return builder.build();
    }
}
