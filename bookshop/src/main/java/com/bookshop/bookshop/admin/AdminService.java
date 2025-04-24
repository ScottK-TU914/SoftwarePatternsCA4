package com.bookshop.bookshop.admin;


//service interface for proxy pattern
public interface AdminService {
    void increaseStock(Long bookId, int amount);
}
