package com.bookshop.bookshop.admin;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceProxy implements AdminService {

    private final AdminServiceImpl realService;

    public AdminServiceProxy(AdminServiceImpl realService) {
        this.realService = realService;
    }

    @Override
    public void increaseStock(Long bookId, int quantity) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {

            realService.increaseStock(bookId, quantity);
        } else {
            throw new SecurityException("Only admins can increase stock levels.");
        }
    }
}
