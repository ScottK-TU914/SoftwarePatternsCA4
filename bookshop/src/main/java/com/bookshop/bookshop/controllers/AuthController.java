package com.bookshop.bookshop.controllers;

import com.bookshop.bookshop.models.Customer;
import com.bookshop.bookshop.repositories.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final CustomerRepository customerRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(CustomerRepository customerRepo, BCryptPasswordEncoder encoder) {
        this.customerRepo = customerRepo;
        this.passwordEncoder = encoder;
    }
//will show the registration form (register.html)
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }
//passes the user's registration with password, username, email and creates a new user for the database.
    @PostMapping("/register")
    public String processRegistration(@RequestParam String username,
                                      @RequestParam String email,
                                      @RequestParam String password) {
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setEmail(email);
        customer.setPassword(passwordEncoder.encode(password));
        customerRepo.save(customer);
        return "redirect:/login";
    }
//displays the login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
