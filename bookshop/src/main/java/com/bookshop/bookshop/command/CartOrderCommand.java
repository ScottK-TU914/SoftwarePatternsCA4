package com.bookshop.bookshop.command;

import com.bookshop.bookshop.services.OrderService;

public class CartOrderCommand implements OrderCommand {

    private final OrderService orderService;
    private final Long bookId;
    private final String username;
    private final int quantity;

    public CartOrderCommand(OrderService orderService, Long bookId, String username, int quantity) {
        this.orderService = orderService;
        this.bookId = bookId;
        this.username = username;
        this.quantity = quantity;
    }

    @Override
    public void execute() {
        orderService.placeOrder(bookId, username, quantity);
    }
}
