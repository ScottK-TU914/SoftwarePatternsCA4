package com.bookshop.bookshop.services;

import com.bookshop.bookshop.models.Customer;
import com.bookshop.bookshop.repositories.CustomerRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepo;

    public CustomUserDetailsService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepo.findByUsername(username);
        if (customer == null) {
            throw new UsernameNotFoundException("User not found");
        }

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + customer.getRole());

        return new User(
            customer.getUsername(),
            customer.getPassword(),
            Collections.singleton(authority)
        );
    }
}
