package com.bookshop.bookshop.admin;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

//proxy class that adds security to the adminservice 

@Service //spring managed class (bean)
public class AdminServiceProxy implements AdminService {

    private final AdminServiceImpl realService;

    public AdminServiceProxy(AdminServiceImpl realService) {
        this.realService = realService;
    }

    @Override
    public void increaseStock(Long bookId, int quantity) {
        // Gets the current user's details from spring security to see if they have access
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //Checks if the user is authenticated and has "ROLE_ADMIN" as their role
        if (auth != null && auth.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            // If authorized, call to the actual service
            realService.increaseStock(bookId, quantity);
        } else {
            throw new SecurityException("Only admins can increase stock levels.");
        }
    }
}
